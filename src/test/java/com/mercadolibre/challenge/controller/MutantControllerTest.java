package com.mercadolibre.challenge.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.google.common.collect.Lists;
import com.mercadolibre.challenge.AbstractCassandraSpringBootTest;
import com.mercadolibre.challenge.TestConstants;
import com.mercadolibre.challenge.modelDto.MutantDto;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
public class MutantControllerTest extends AbstractCassandraSpringBootTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JacksonTester<MutantDto> jsonMutantDto;

	@Test
	public void validRequestMutant() throws Exception {
		JsonContent<MutantDto> write = jsonMutantDto.write(new MutantDto(Lists.newArrayList(TestConstants.MUTANT_DNA)));

		porformPost(write, status().isOk());
	}

	@Test
	public void validRequestNotMutan() throws Exception {
		JsonContent<MutantDto> write = jsonMutantDto.write(new MutantDto(Lists.newArrayList(TestConstants.NOT_MUTANT_DNA)));

		porformPost(write, status().isForbidden());
	}

	// --- Private methods

	private MockHttpServletResponse porformPost(JsonContent<MutantDto> write, ResultMatcher status) throws Exception {
		//@formatter:off
		return this.mvc.perform(post("/mutant")
				.contentType(MediaType.APPLICATION_JSON)
				.content(write.getJson()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status)
				.andReturn().getResponse();
		//@formatter:on
	}
}
