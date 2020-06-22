package com.mercadolibre.challenge.utils.checkers;

import org.junit.jupiter.api.Test;

import com.mercadolibre.challenge.testUtils.TestConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NegativeDiagonalCheckerTest extends AbstractLineCheckerTest {

	@Override
	protected LineChecker getLineChecker() {
		return new NegativeDiagonalChecker();
	}

	@Test
	@Override
	public void testGetLines() {
		log.info("--- Negative Diagonal Lines Test ---");

		LineChecker checker = getLineChecker();

		log.info("------ Mutant DNA on example");
		assertArrayContainingInAnyOrder(TestConstants.MUTANT_NEGATIVE_DIAGONAL, checker.getLines(TestConstants.MUTANT_DNA));

		log.info("------ Not Mutant DNA on example");
		assertArrayContainingInAnyOrder(TestConstants.NOT_MUTANT_NEGATIVE_DIAGONAL, checker.getLines(TestConstants.NOT_MUTANT_DNA));

		assertArrayContainingInAnyOrder(new String[] { "2", "14", "3" }, checker.getLines(new String[] { "12", "34" }));
		assertArrayContainingInAnyOrder(new String[] { "3", "26", "159", "48", "7" }, checker.getLines(new String[] { "123", "456", "789" }));
		assertArrayContainingInAnyOrder(new String[] { "D", "CH", "BGL", "AFKP", "EJO", "IN", "M" }, checker.getLines(new String[] { "ABCD", "EFGH", "IJKL", "MNOP" }));
	}
}
