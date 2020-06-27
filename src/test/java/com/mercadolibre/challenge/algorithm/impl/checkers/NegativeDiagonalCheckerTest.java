package com.mercadolibre.challenge.algorithm.impl.checkers;

import static com.mercadolibre.challenge.test.utilities.TestConstants.ARRAY_4X4;
import static com.mercadolibre.challenge.test.utilities.TestConstants.ARRAY_5X5;
import static com.mercadolibre.challenge.test.utilities.TestConstants.ARRAY_7X7;
import static com.mercadolibre.challenge.test.utilities.TestConstants.MUTANT_DNA;
import static com.mercadolibre.challenge.test.utilities.TestConstants.MUTANT_NEGATIVE_DIAGONAL;
import static com.mercadolibre.challenge.test.utilities.TestConstants.NOT_MUTANT_DNA;
import static com.mercadolibre.challenge.test.utilities.TestConstants.NOT_MUTANT_NEGATIVE_DIAGONAL;

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

		log.info("------ Mutant DNA on example:");
		assertArrayContainingInAnyOrder(MUTANT_NEGATIVE_DIAGONAL, MUTANT_DNA);

		log.info("------ Not Mutant DNA on example:");
		assertArrayContainingInAnyOrder(NOT_MUTANT_NEGATIVE_DIAGONAL, NOT_MUTANT_DNA);

		assertArrayContainingInAnyOrder(new String[] { "AFKP" }, ARRAY_4X4);
		assertArrayContainingInAnyOrder(new String[] { "AGMSY", "BHNT", "FLRX" }, ARRAY_5X5);
		assertArrayContainingInAnyOrder(new String[] { "AIQYGOW", "HPXFNV", "OWEMU", "VDLT", "BJRZHP", "CKSAI", "DLTB" }, ARRAY_7X7);
	}
}
