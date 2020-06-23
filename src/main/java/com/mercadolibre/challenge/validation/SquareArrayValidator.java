package com.mercadolibre.challenge.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Chequea que la longitud de todos los elementos de la lista sea igual al size de la lista (NxN)
 * @author epallich
 */
public class SquareArrayValidator implements ConstraintValidator<SquareArray, List<String>> {

	@Override
	public boolean isValid(List<String> value, ConstraintValidatorContext context) {
		if (value != null && value.size() > 0)
			return value.stream().map(String::length).distinct().allMatch(x -> x == value.size());

		return false;
	}

}
