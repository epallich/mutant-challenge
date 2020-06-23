package com.mercadolibre.challenge.utils.checkers;

import org.junit.jupiter.api.Test;

import com.mercadolibre.challenge.testUtils.TestConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PositiveDiagonalCheckerTest extends AbstractLineCheckerTest {

	@Override
	protected LineChecker getLineChecker() {
		return new PositiveDiagonalChecker();
	}

	@Test
	@Override
	public void testGetLines() {
		log.info("--- Positive Diagonal Lines Test ---");

		LineChecker checker = getLineChecker();

		log.info("------ Mutant DNA");
		assertArrayContainingInAnyOrder(TestConstants.MUTANT_POSITIVE_DIAGONAL, checker.getLines(TestConstants.MUTANT_DNA));

		log.info("------ Not Mutant DNA");
		assertArrayContainingInAnyOrder(TestConstants.NOT_MUTANT_POSITIVE_DIAGONAL, checker.getLines(TestConstants.NOT_MUTANT_DNA));

		assertArrayContainingInAnyOrder(new String[] { "1", "32", "4" }, checker.getLines(new String[] { "12", "34" }));
		assertArrayContainingInAnyOrder(new String[] { "1", "42", "753", "86", "9" }, checker.getLines(new String[] { "123", "456", "789" }));
		assertArrayContainingInAnyOrder(new String[] { "A", "EB", "IFC", "MJGD", "NKH", "OL", "P" }, checker.getLines(new String[] { "ABCD", "EFGH", "IJKL", "MNOP" }));
	}
}
