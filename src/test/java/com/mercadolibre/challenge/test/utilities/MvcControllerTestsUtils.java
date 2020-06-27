package com.mercadolibre.challenge.test.utilities;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.boot.test.json.JsonContent;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.google.common.collect.Lists;
import com.mercadolibre.challenge.dto.MutantDto;

public final class MvcControllerTestsUtils {

	private MvcControllerTestsUtils() {
		// Do nothing
	}

	public static MockHttpServletResponse porformPost(MockMvc mockMvc, JsonContent<MutantDto> write, ResultMatcher status) throws Exception {
		//@formatter:off
		
		return mockMvc.perform(post("/mutant")
					.contentType(MediaType.APPLICATION_JSON)
					.content(write.getJson()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status)
				.andReturn().getResponse();
		
		//@formatter:on
	}

	public static MockHttpServletResponse porformGetStats(MockMvc mockMvc, ResultMatcher status) throws Exception {
		//@formatter:off
		
		return mockMvc.perform(get("/stats")
					.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status)
				.andReturn().getResponse();
		
		//@formatter:on
	}

	public static MutantDto getMutant() {
		return new MutantDto(Lists.newArrayList(TestConstants.MUTANT_DNA));
	}

	public static MutantDto getHuman() {
		return new MutantDto(Lists.newArrayList(TestConstants.NOT_MUTANT_DNA));
	}

}
