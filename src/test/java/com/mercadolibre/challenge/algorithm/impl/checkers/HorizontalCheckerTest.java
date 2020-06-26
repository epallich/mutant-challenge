package com.mercadolibre.challenge.algorithm.impl.checkers;

import static com.mercadolibre.challenge.TestConstants.ARRAY_2X2;
import static com.mercadolibre.challenge.TestConstants.ARRAY_3X3;
import static com.mercadolibre.challenge.TestConstants.ARRAY_4X4;
import static com.mercadolibre.challenge.TestConstants.MUTANT_DNA;
import static com.mercadolibre.challenge.TestConstants.NOT_MUTANT_DNA;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HorizontalCheckerTest extends AbstractLineCheckerTest {

	@Override
	protected AbstractLineChecker getLineChecker() {
		return new HorizontalChecker();
	}

	@Test
	@Override
	public void testGetLines() {
		log.info("--- Horizontal Lines Test ---");

		AbstractLineChecker checker = getLineChecker();

		log.info("------ Mutant DNA on example");
		assertArrayContainingInAnyOrder(MUTANT_DNA, checker.getLines(MUTANT_DNA));

		log.info("------ Not Mutant DNA on example");
		assertArrayContainingInAnyOrder(NOT_MUTANT_DNA, checker.getLines(NOT_MUTANT_DNA));

		assertArrayContainingInAnyOrder(new String[] { "AB", "CD" }, checker.getLines(ARRAY_2X2));
		assertArrayContainingInAnyOrder(new String[] { "ABC", "DEF", "GHI" }, checker.getLines(ARRAY_3X3));
		assertArrayContainingInAnyOrder(new String[] { "ABCD", "EFGH", "IJKL", "MNOP" }, checker.getLines(ARRAY_4X4));

	}

}
