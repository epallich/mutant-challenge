package com.mercadolibre.challenge.utils;

import com.mercadolibre.challenge.utils.checkers.HorizontalChecker;
import com.mercadolibre.challenge.utils.checkers.LineChecker;
import com.mercadolibre.challenge.utils.checkers.NegativeDiagonalChecker;
import com.mercadolibre.challenge.utils.checkers.PositiveDiagonalChecker;
import com.mercadolibre.challenge.utils.checkers.VerticalChecker;

/**
 * Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras iguales, de forma oblicua, horizontal o vertical.
 * @author epallich
 */
public class MutantFinder implements MutantFinderInterface {

	// TODO
	protected static final int MIN_LENGTH = 4;
	protected static LineChecker lineChecker;

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
