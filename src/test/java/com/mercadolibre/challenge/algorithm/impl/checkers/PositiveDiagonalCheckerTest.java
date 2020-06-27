package com.mercadolibre.challenge.algorithm.impl.checkers;

import static com.mercadolibre.challenge.test.utilities.TestConstants.ARRAY_4X4;
import static com.mercadolibre.challenge.test.utilities.TestConstants.ARRAY_5X5;
import static com.mercadolibre.challenge.test.utilities.TestConstants.ARRAY_7X7;
import static com.mercadolibre.challenge.test.utilities.TestConstants.MUTANT_DNA;
import static com.mercadolibre.challenge.test.utilities.TestConstants.MUTANT_POSITIVE_DIAGONAL;
import static com.mercadolibre.challenge.test.utilities.TestConstants.NOT_MUTANT_DNA;
import static com.mercadolibre.challenge.test.utilities.TestConstants.NOT_MUTANT_POSITIVE_DIAGONAL;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PositiveDiagonalCheckerTest extends AbstractLineCheckerTest {

	@Override
	protected AbstractLineChecker getLineChecker() {
		return new PositiveDiagonalChecker();
	}

	@Test
	@Override
	public void testGetLines() {
		log.info("--- Positive Diagonal Lines Test ---");

		log.info("------ Mutant DNA:");
		assertArrayContainingInAnyOrder(MUTANT_POSITIVE_DIAGONAL, MUTANT_DNA);

		log.info("------ Not Mutant DNA:");
		assertArrayContainingInAnyOrder(NOT_MUTANT_POSITIVE_DIAGONAL, NOT_MUTANT_DNA);

		assertArrayContainingInAnyOrder(new String[] { "DGJM" }, ARRAY_4X4);
		assertArrayContainingInAnyOrder(new String[] { "DHLP", "EIMQU", "JNRV" }, ARRAY_5X5);
		assertArrayContainingInAnyOrder(new String[] { "BHNT", "UAGMS", "NTZFLR", "GMSYEKQ", "FLRXDJ", "EKQWC", "DJPV" }, ARRAY_7X7);
	}
}
