package com.mercadolibre.challenge.algorithm.impl.checkers;

import static com.mercadolibre.challenge.TestConstants.ARRAY_2X2;
import static com.mercadolibre.challenge.TestConstants.ARRAY_3X3;
import static com.mercadolibre.challenge.TestConstants.ARRAY_4X4;
import static com.mercadolibre.challenge.TestConstants.ARRAY_5X5;
import static com.mercadolibre.challenge.TestConstants.ARRAY_7X7;
import static com.mercadolibre.challenge.TestConstants.MUTANT_DNA;
import static com.mercadolibre.challenge.TestConstants.MUTANT_VERTICAL;
import static com.mercadolibre.challenge.TestConstants.NOT_MUTANT_DNA;
import static com.mercadolibre.challenge.TestConstants.NOT_MUTANT_VERTICAL;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VerticalCheckerTest extends AbstractLineCheckerTest {

	@Override
	protected AbstractLineChecker getLineChecker() {
		return new VerticalChecker();
	}

	@Test
	@Override
	public void testGetLines() {
		log.info("--- Vertical Lines Test ---");

		AbstractLineChecker checker = getLineChecker();

		log.info("------ Mutant DNA on example");
		assertArrayContainingInAnyOrder(MUTANT_VERTICAL, checker.getLines(MUTANT_DNA));

		log.info("------ Not Mutant DNA on example");
		assertArrayContainingInAnyOrder(NOT_MUTANT_VERTICAL, checker.getLines(NOT_MUTANT_DNA));

		// Expected - Original
		assertArrayContainingInAnyOrder(new String[] { "AC", "BD" }, checker.getLines(ARRAY_2X2));
		assertArrayContainingInAnyOrder(new String[] { "ADG", "BEH", "CFI" }, checker.getLines(ARRAY_3X3));
		assertArrayContainingInAnyOrder(new String[] { "AEIM", "BFJN", "CGKO", "DHLP" }, checker.getLines(ARRAY_4X4));
		assertArrayContainingInAnyOrder(new String[] { "AFKPU", "BGLQV", "CHMRW", "DINSX", "EJOTY" }, checker.getLines(ARRAY_5X5));
		assertArrayContainingInAnyOrder(new String[] { "AHOVCJQ", "BIPWDKR", "CJQXELS", "DKRYFMT", "ELSZGNU", "FMTAHOV", "GNUBIPW" }, checker.getLines(ARRAY_7X7));

	}
}
