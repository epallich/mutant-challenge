package com.mercadolibre.challenge.algorithm.impl.checkers;

import static com.mercadolibre.challenge.TestConstants.ARRAY_2X2;
import static com.mercadolibre.challenge.TestConstants.ARRAY_3X3;
import static com.mercadolibre.challenge.TestConstants.ARRAY_4X4;
import static com.mercadolibre.challenge.TestConstants.ARRAY_5X5;
import static com.mercadolibre.challenge.TestConstants.ARRAY_7X7;
import static com.mercadolibre.challenge.TestConstants.MUTANT_DNA;
import static com.mercadolibre.challenge.TestConstants.MUTANT_POSITIVE_DIAGONAL;
import static com.mercadolibre.challenge.TestConstants.NOT_MUTANT_DNA;
import static com.mercadolibre.challenge.TestConstants.NOT_MUTANT_POSITIVE_DIAGONAL;

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

		AbstractLineChecker checker = getLineChecker();

		log.info("------ Mutant DNA");
		assertArrayContainingInAnyOrder(MUTANT_POSITIVE_DIAGONAL, checker.getLines(MUTANT_DNA));

		log.info("------ Not Mutant DNA");
		assertArrayContainingInAnyOrder(NOT_MUTANT_POSITIVE_DIAGONAL, checker.getLines(NOT_MUTANT_DNA));

		assertArrayContainingInAnyOrder(new String[] {}, checker.getLines(ARRAY_2X2));
		assertArrayContainingInAnyOrder(new String[] {}, checker.getLines(ARRAY_3X3));
		assertArrayContainingInAnyOrder(new String[] { "DGJM" }, checker.getLines(ARRAY_4X4));
		assertArrayContainingInAnyOrder(new String[] { "DHLP", "EIMQU", "JNRV" }, checker.getLines(ARRAY_5X5));
		assertArrayContainingInAnyOrder(new String[] { "BHNT", "UAGMS", "NTZFLR", "GMSYEKQ", "FLRXDJ", "EKQWC", "DJPV" }, checker.getLines(ARRAY_7X7));
	}
}
