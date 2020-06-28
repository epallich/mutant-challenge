package com.mercadolibre.challenge.algorithm.impl.checkers;

public final class HorizontalChecker extends AbstractLineChecker {

	/**
	 * Returns the horizontal lines
	 */
	@Override
	protected final String[] getLines(String[] dna) {

		return dna.clone();
	}

}
