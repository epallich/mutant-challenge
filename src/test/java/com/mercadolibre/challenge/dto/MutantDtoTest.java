package com.mercadolibre.challenge.dto;

import javax.validation.groups.Default;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;
import com.mercadolibre.challenge.dto.MutantDto;
import com.mercadolibre.challenge.validation.SecondCheck;

public class MutantDtoTest extends AbstractDtoTest<MutantDto> {

	private static final String PATTERN_MESSAGE = "There is some invalid character.";
	private static final String NOT_EMPTY_MESSAGE = "{javax.validation.constraints.NotEmpty.message}";
	private static final String SQUARE_MESSAGE = "The array must be square (NxN).";

	@Test
	public void notNullDnaTest() throws Exception {
		final MutantDto mutantDto = new MutantDto();
		doMessageTemplateValidationWithErrors(mutantDto, Lists.newArrayList(NOT_EMPTY_MESSAGE));
	}

	@Test
	public void notEmptyDnaTest() throws Exception {
		final MutantDto mutantDto = new MutantDto(Lists.newArrayList());
		doMessageTemplateValidationWithErrors(mutantDto, Lists.newArrayList(NOT_EMPTY_MESSAGE));
	}

	@Test
	public void notNullLineTest() throws Exception {
		final MutantDto mutantDto = new MutantDto(Lists.newArrayList(null, "C"));
		doMessageTemplateValidationWithErrors(mutantDto, Lists.newArrayList(NOT_EMPTY_MESSAGE));
	}

	@Test
	public void notEmptyLineTest() throws Exception {
		final MutantDto mutantDto = new MutantDto(Lists.newArrayList("", "C"));
		doMessageTemplateValidationWithErrors(mutantDto, Lists.newArrayList(NOT_EMPTY_MESSAGE));
	}

	@Test
	public void notSquareDnaTest() throws Exception {
		final MutantDto mutantDto = new MutantDto(Lists.newArrayList("AA"));
		doMessageValidationWithErrors(mutantDto, Lists.newArrayList(SQUARE_MESSAGE), SecondCheck.class);
	}

	@Test
	public void notValidLineTest() throws Exception {
		final MutantDto mutantDto = new MutantDto(Lists.newArrayList("ATCG", "GTAT", "TTAC", "ZZZZ"));
		doMessageTemplateValidationWithErrors(mutantDto, Lists.newArrayList(PATTERN_MESSAGE), SecondCheck.class);
	}

	@Test
	public void testValidatorNoViolations() {
		final MutantDto dto = new MutantDto(Lists.newArrayList("AA", "CC"));
		doValidationWithoutErrors(dto, Default.class, SecondCheck.class);
	}
}
