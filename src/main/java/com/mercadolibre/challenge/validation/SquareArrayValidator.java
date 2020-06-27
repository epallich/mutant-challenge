package com.mercadolibre.challenge.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Checking the length of all the elements within the list, must be equal to the size of the list (NxN)
 * @author epallich
 */
public class SquareArrayValidator implements ConstraintValidator<SquareArray, List<String>> {

	@Override
	public boolean isValid(List<String> value, ConstraintValidatorContext context) {
		if (value != null && !value.isEmpty())
			return value.stream().map(String::length).distinct().allMatch(x -> x == value.size());

		return false;
	}

}
