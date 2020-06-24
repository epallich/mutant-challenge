package com.mercadolibre.challenge.domain;

public enum PersonType {

	// @formatter:off
	MUTANT, 
	HUMAN;
	// @formatter:on

	public static String getPersonType(boolean isMutant) {
		return isMutant ? MUTANT.name() : HUMAN.name();
	}
}
