package com.mercadolibre.challenge.algorithm.impl.checkers;

import static com.mercadolibre.challenge.TestConstants.ARRAY_2X2;
import static com.mercadolibre.challenge.TestConstants.ARRAY_3X3;
import static com.mercadolibre.challenge.TestConstants.ARRAY_4X4;
import static com.mercadolibre.challenge.TestConstants.ARRAY_5X5;
import static com.mercadolibre.challenge.TestConstants.ARRAY_7X7;
import static com.mercadolibre.challenge.TestConstants.MUTANT_DNA;
import static com.mercadolibre.challenge.TestConstants.MUTANT_NEGATIVE_DIAGONAL;
import static com.mercadolibre.challenge.TestConstants.NOT_MUTANT_DNA;
import static com.mercadolibre.challenge.TestConstants.NOT_MUTANT_NEGATIVE_DIAGONAL;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NegativeDiagonalCheckerTest extends AbstractLineCheckerTest {

	@Override
	protected AbstractLineChecker getLineChecker() {
		return new NegativeDiagonalChecker();
	}

	@Test
	@Override
	public void testGetLines() {
		log.info("--- Negative Diagonal Lines Test ---");

		AbstractLineChecker checker = getLineChecker();

		log.info("------ Mutant DNA on example");
		assertArrayContainingInAnyOrder(MUTANT_NEGATIVE_DIAGONAL, checker.getLines(MUTANT_DNA));

		log.info("------ Not Mutant DNA on example");
		assertArrayContainingInAnyOrder(NOT_MUTANT_NEGATIVE_DIAGONAL, checker.getLines(NOT_MUTANT_DNA));

		assertArrayContainingInAnyOrder(new String[] {}, checker.getLines(ARRAY_2X2));
		assertArrayContainingInAnyOrder(new String[] {}, checker.getLines(ARRAY_3X3));
		assertArrayContainingInAnyOrder(new String[] { "AFKP" }, checker.getLines(ARRAY_4X4));
		assertArrayContainingInAnyOrder(new String[] { "AGMSY", "BHNT", "FLRX" }, checker.getLines(ARRAY_5X5));
		assertArrayContainingInAnyOrder(new String[] { "AIQYGOW", "HPXFNV", "OWEMU", "VDLT", "BJRZHP", "CKSAI", "DLTB" }, checker.getLines(ARRAY_7X7));
	}
}
