package com.mercadolibre.challenge.modelDto;

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

import com.tocea.easycoverage.framework.checkers.ArrayIndexOutOfBoundExceptionChecker;
import com.tocea.easycoverage.framework.checkers.BijectiveCompareToChecker;
import com.tocea.easycoverage.framework.checkers.BijectiveEqualsChecker;
import com.tocea.easycoverage.framework.checkers.CloneChecker;
import com.tocea.easycoverage.framework.checkers.NPEConstructorChecker;
import com.tocea.easycoverage.framework.checkers.NPEMethodChecker;
import com.tocea.easycoverage.framework.checkers.NullValueEqualsChecker;
import com.tocea.easycoverage.framework.checkers.SetterChecker;
import com.tocea.easycoverage.framework.checkers.ToStringNotNullChecker;
import com.tocea.easycoverage.framework.junit.JUnitTestSuiteProvider;

import junit.framework.TestSuite;

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

	protected TestSuite getTestSuite(Class<T> clazz) {
		JUnitTestSuiteProvider testSuiteProvider = new JUnitTestSuiteProvider();

		testSuiteProvider.addClass(clazz);

		testSuiteProvider.addClassChecker(ToStringNotNullChecker.class);
		testSuiteProvider.addClassChecker(BijectiveCompareToChecker.class);
		testSuiteProvider.addClassChecker(BijectiveEqualsChecker.class);
		testSuiteProvider.addClassChecker(CloneChecker.class);
		testSuiteProvider.addClassChecker(NPEConstructorChecker.class);
		testSuiteProvider.addClassChecker(NullValueEqualsChecker.class);

		testSuiteProvider.addMethodChecker(NPEMethodChecker.class);
		testSuiteProvider.addMethodChecker(SetterChecker.class);
		testSuiteProvider.addMethodChecker(ArrayIndexOutOfBoundExceptionChecker.class);

		return testSuiteProvider.getTestSuite();
	}

}
