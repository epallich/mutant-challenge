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

@Slf4j
@Getter
public class MutantFinder implements MutantFinderInterface {

	private static AbstractLineChecker lineChecker;

	public static final MutantFinder INSTANCE = new MutantFinder();

	// @formatter:off
	
	private MutantFinder() {
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
