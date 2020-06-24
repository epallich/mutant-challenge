package com.mercadolibre.challenge.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;
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
import com.mercadolibre.challenge.modelDto.MutantDto;
import com.mercadolibre.challenge.testUtils.AbstractCassandraSpringBootTest;
import com.mercadolibre.challenge.testUtils.TestConstants;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
public class MutantControllerTest extends AbstractCassandraSpringBootTest {

	private static final String MUST_NOT_BE_EMPTY = "must not be empty";
	private static final String INVALID_ARRAY = "El array es invalido, vacio o nulo. Debe ser cuadrado (NxN).";

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JacksonTester<MutantDto> jsonMutantDto;

	// * 1. dna: null -> not empty

	@Test
	public void notNullDnaTest() throws Exception {
		// given
		JsonContent<MutantDto> write = jsonMutantDto.write(new MutantDto(null));

		// when
		MockHttpServletResponse response = porformPost(write, status().isBadRequest());

		// then
		Assertions.assertThat(response.getContentAsString()).contains(MUST_NOT_BE_EMPTY);
	}

	// * 2: dna: [] -> not empty

	@Test
	public void notEmptyDnaTest() throws Exception {
		// given
		JsonContent<MutantDto> write = jsonMutantDto.write(new MutantDto(Lists.newArrayList()));

		// when
		MockHttpServletResponse response = porformPost(write, status().isBadRequest());

		// then
		Assertions.assertThat(response.getContentAsString()).contains(MUST_NOT_BE_EMPTY);
	}

	// * 3: dna: ["AA"] -> no es matriz cuadrada

	@Test
	public void notSquareDnaTest() throws Exception {
		// given
		JsonContent<MutantDto> write = jsonMutantDto.write(new MutantDto(Lists.newArrayList("AA")));

		// when
		MockHttpServletResponse response = porformPost(write, status().isBadRequest());

		// then
		Assertions.assertThat(response.getContentAsString()).contains(INVALID_ARRAY);
	}

	// * 4: dna: [null, "C"] -> element not null

	@Test
	public void notNullLineTest() throws Exception {
		// given
		JsonContent<MutantDto> write = jsonMutantDto.write(new MutantDto(Lists.newArrayList(null, "C")));

		// when
		MockHttpServletResponse response = porformPost(write, status().isBadRequest());

		// then
		Assertions.assertThat(response.getContentAsString()).contains(MUST_NOT_BE_EMPTY);
	}

	// * 5: dna: ["", "C"] -> element not empty

	@Test
	public void notEmptyLineTest() throws Exception {
		// given
		JsonContent<MutantDto> write = jsonMutantDto.write(new MutantDto(Lists.newArrayList("", "C")));

		// when
		MockHttpServletResponse response = porformPost(write, status().isBadRequest());

		// then
		Assertions.assertThat(response.getContentAsString()).contains(MUST_NOT_BE_EMPTY);
	}

	// * 6: dna: ["ATCG","GTAT","TTAC", "ZZZZ"] -> caracter invalido

	@Test
	public void notValidLineTest() throws Exception {
		// given
		JsonContent<MutantDto> write = jsonMutantDto.write(new MutantDto(Lists.newArrayList("ATCG", "GTAT", "TTAC", "ZZZZ")));

		// when
		MockHttpServletResponse response = porformPost(write, status().isBadRequest());

		// then
		Assertions.assertThat(response.getContentAsString()).contains("Hay algun caracter invalido");
	}

	// * 7. dna: ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] -> OK Mutant

	@Test
	public void validRequestMutant() throws Exception {
		// given
		JsonContent<MutantDto> write = jsonMutantDto.write(new MutantDto(Lists.newArrayList(TestConstants.MUTANT_DNA)));

		// when
		porformPost(write, status().isOk());
	}

	// * 7. dna: ["ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"] -> Forbidden Not Mutant

	@Test
	public void validRequestNotMutan() throws Exception {
		// given
		JsonContent<MutantDto> write = jsonMutantDto.write(new MutantDto(Lists.newArrayList(TestConstants.NOT_MUTANT_DNA)));

		// when
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
