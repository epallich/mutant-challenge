package com.mercadolibre.challenge.handler;

import static com.mercadolibre.challenge.test.utilities.MvcControllerTestsUtils.getMutant;
import static com.mercadolibre.challenge.test.utilities.MvcControllerTestsUtils.porformPost;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.mercadolibre.challenge.controller.MutantController;
import com.mercadolibre.challenge.dto.MutantDto;
import com.mercadolibre.challenge.service.MutantService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MutantController.class)
@AutoConfigureJsonTesters
public class ExceptionHandlerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private MutantService mutantService;

	@Autowired
	private JacksonTester<MutantDto> jsonMutantDto;

	@Test
	public void invalidRequest400() throws Exception {

		MockHttpServletResponse response = porformPost(mvc, jsonMutantDto.write(new MutantDto()), status().isBadRequest());

		assertThat(response.getContentAsString()).isNotEmpty().contains("");
	}

	@Test
	public void invalidRequest500() throws Exception {
		when(mutantService.isMutant(any())).thenThrow(RuntimeException.class);

		porformPost(mvc, jsonMutantDto.write(getMutant()), status().isInternalServerError());
	}

}
