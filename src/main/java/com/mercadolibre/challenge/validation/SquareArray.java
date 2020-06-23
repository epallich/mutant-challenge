package com.mercadolibre.challenge.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = SquareArrayValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SquareArray {
	String message() default "El array es invalido, vacio o nulo. Debe ser cuadrado (NxN).";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
