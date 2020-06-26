package com.mercadolibre.challenge.algorithm.impl;

import com.mercadolibre.challenge.algorithm.MutantFinderInterface;
import com.mercadolibre.challenge.algorithm.impl.checkers.HorizontalChecker;
import com.mercadolibre.challenge.algorithm.impl.checkers.AbstractLineChecker;
import com.mercadolibre.challenge.algorithm.impl.checkers.NegativeDiagonalChecker;
import com.mercadolibre.challenge.algorithm.impl.checkers.PositiveDiagonalChecker;
import com.mercadolibre.challenge.algorithm.impl.checkers.VerticalChecker;

/**
 * Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras iguales, de forma oblicua, horizontal o vertical.
 * @author epallich
 */
public class MutantFinder implements MutantFinderInterface {

	// TODO
	protected static final int MIN_LENGTH = 4;
	protected static AbstractLineChecker lineChecker;

	public static MutantFinder INSTANCE = new MutantFinder();

	// @formatter:off
	private MutantFinder() {
		lineChecker = new HorizontalChecker();
		lineChecker.linkWith(new VerticalChecker())
			.linkWith(new NegativeDiagonalChecker())
			.linkWith(new PositiveDiagonalChecker());
	}
	// @formatter:on

	@Override
	public boolean isMutant(String[] dna) {

		if (dna.length < MIN_LENGTH)
			return false;

		return lineChecker.checkDnaSequence(dna, 0);
	}

}
