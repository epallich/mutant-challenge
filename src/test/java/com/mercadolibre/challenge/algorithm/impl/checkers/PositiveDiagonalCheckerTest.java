package com.mercadolibre.challenge.algorithm.impl.checkers;

import org.junit.jupiter.api.Test;

import com.mercadolibre.challenge.TestConstants;

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
		assertArrayContainingInAnyOrder(TestConstants.MUTANT_POSITIVE_DIAGONAL, checker.getLines(TestConstants.MUTANT_DNA));

		log.info("------ Not Mutant DNA");
		assertArrayContainingInAnyOrder(TestConstants.NOT_MUTANT_POSITIVE_DIAGONAL, checker.getLines(TestConstants.NOT_MUTANT_DNA));

		assertArrayContainingInAnyOrder(new String[] {}, checker.getLines(new String[] { "12", "34" }));
		assertArrayContainingInAnyOrder(new String[] {}, checker.getLines(new String[] { "123", "456", "789" }));
		assertArrayContainingInAnyOrder(new String[] { "DGJM" }, checker.getLines(new String[] { "ABCD", "EFGH", "IJKL", "MNOP" }));
	}
}