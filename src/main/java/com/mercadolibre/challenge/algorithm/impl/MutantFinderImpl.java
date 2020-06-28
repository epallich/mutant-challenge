package com.mercadolibre.challenge.algorithm.impl;

import static com.mercadolibre.challenge.ApplicationConstants.MIN_LENGTH;

import com.mercadolibre.challenge.algorithm.MutantFinderInterface;
import com.mercadolibre.challenge.algorithm.impl.checkers.AbstractLineChecker;
import com.mercadolibre.challenge.algorithm.impl.checkers.HorizontalChecker;
import com.mercadolibre.challenge.algorithm.impl.checkers.NegativeDiagonalChecker;
import com.mercadolibre.challenge.algorithm.impl.checkers.PositiveDiagonalChecker;
import com.mercadolibre.challenge.algorithm.impl.checkers.VerticalChecker;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Mutant finder implementation
 * @author epallich
 */
@Slf4j
@Getter
public class MutantFinderImpl implements MutantFinderInterface {

	private static AbstractLineChecker lineChecker;

	public static final MutantFinderImpl INSTANCE = new MutantFinderImpl();

	// @formatter:off
	
	/**
	 * Creates a sequencial chain of checkers
	 */
	private MutantFinderImpl() {
		lineChecker = new HorizontalChecker();
		lineChecker.linkWith(new VerticalChecker())
			.linkWith(new NegativeDiagonalChecker())
			.linkWith(new PositiveDiagonalChecker());
	}
	
	// @formatter:on

	@Override
	public boolean isMutant(final String[] dna) {
		log.info("Checking DNA: [{}]", String.join(",", dna));
		if (dna.length < MIN_LENGTH)
			return false;

		return lineChecker.checkDnaSequence(dna, 0);
	}

}
