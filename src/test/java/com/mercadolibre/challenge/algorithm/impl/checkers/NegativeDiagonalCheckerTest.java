package com.mercadolibre.challenge.algorithm.impl.checkers;

import org.junit.jupiter.api.Test;

import com.mercadolibre.challenge.TestConstants;

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
		assertArrayContainingInAnyOrder(TestConstants.MUTANT_NEGATIVE_DIAGONAL, checker.getLines(TestConstants.MUTANT_DNA));

		log.info("------ Not Mutant DNA on example");
		assertArrayContainingInAnyOrder(TestConstants.NOT_MUTANT_NEGATIVE_DIAGONAL, checker.getLines(TestConstants.NOT_MUTANT_DNA));

		assertArrayContainingInAnyOrder(new String[] {}, checker.getLines(new String[] { "12", "34" }));
		assertArrayContainingInAnyOrder(new String[] {}, checker.getLines(new String[] { "123", "456", "789" }));
		assertArrayContainingInAnyOrder(new String[] { "AFKP" }, checker.getLines(new String[] { "ABCD", "EFGH", "IJKL", "MNOP" }));
	}
}
