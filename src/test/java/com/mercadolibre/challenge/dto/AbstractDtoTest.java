package com.mercadolibre.challenge.dto;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class AbstractDtoTest<T> {

	protected static Validator validator;
	protected static ValidatorFactory validatorFactory;

	@BeforeAll
	public static void setup() {
		validatorFactory = Validation.byDefaultProvider().configure().buildValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@AfterAll
	public static void relase() {
		if (validatorFactory != null) {
			validatorFactory.close();
			validatorFactory = null;
			validator = null;
		}
	}

	protected void doValidationWithErrors(final Function<ConstraintViolation<T>, String> mapper, final T dto, final List<String> expectedErrors, final Class<?>... groups) {
		final Set<ConstraintViolation<T>> constraintViolations = validator.validate(dto, groups);
		Assert.assertEquals(expectedErrors.size(), constraintViolations.size());
		Assert.assertFalse(constraintViolations.isEmpty());
		Assert.assertTrue(constraintViolations.stream().map(mapper).collect(Collectors.toSet()).containsAll(expectedErrors));
	}

	protected void doMessageValidationWithErrors(final T dto, final List<String> errors, final Class<?>... groups) {
		doValidationWithErrors(v -> v.getMessage(), dto, errors, groups);
	}

	protected void doMessageTemplateValidationWithErrors(final T dto, final List<String> errors, final Class<?>... groups) {
		doValidationWithErrors(v -> v.getMessageTemplate(), dto, errors, groups);
	}

	protected void doValidationWithoutErrors(final T dto, final Class<?>... groups) {
		final Set<ConstraintViolation<T>> violations = validator.validate(dto, groups);
		Assert.assertTrue(violations.isEmpty());
	}

}
