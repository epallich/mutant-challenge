package com.mercadolibre.challenge.algorithm.impl;

import static com.mercadolibre.challenge.ApplicationConstants.MIN_LENGTH;

import com.mercadolibre.challenge.algorithm.MutantFinderInterface;
import com.mercadolibre.challenge.algorithm.impl.checkers.AbstractLineChecker;
import com.mercadolibre.challenge.algorithm.impl.checkers.HorizontalChecker;
import com.mercadolibre.challenge.algorithm.impl.checkers.NegativeDiagonalChecker;
import com.mercadolibre.challenge.algorithm.impl.checkers.PositiveDiagonalChecker;
import com.mercadolibre.challenge.algorithm.impl.checkers.VerticalChecker;

import lombok.extern.slf4j.Slf4j;

/**
 * You will know if a human is mutant,
 * if you find more than one sequence of four equal letters, obliquely, horizontally or vertically.
 * @author epallich
 */
@Slf4j
public class MutantFinder implements MutantFinderInterface {

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
		log.info("Checking DNA: [{}]", String.join(",", dna));
		if (dna.length < MIN_LENGTH)
			return false;

		return lineChecker.checkDnaSequence(dna, 0);
	}

}
