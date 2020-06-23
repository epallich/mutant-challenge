package com.mercadolibre.challenge.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;

public class SquareArrayValidatorTest {

	@Test
	public void testValidator() {
		SquareArrayValidator validator = new SquareArrayValidator();

		Assertions.assertThat(validator.isValid(null, null)).isFalse();

		Assertions.assertThat(validator.isValid(Lists.newArrayList(), null)).isFalse();

		Assertions.assertThat(validator.isValid(Lists.newArrayList("A"), null)).isTrue();

		Assertions.assertThat(validator.isValid(Lists.newArrayList("AB"), null)).isFalse();

		Assertions.assertThat(validator.isValid(Lists.newArrayList("AB", "AB"), null)).isTrue();

		Assertions.assertThat(validator.isValid(Lists.newArrayList("AB", "AB", "AB"), null)).isFalse();

	}
}
