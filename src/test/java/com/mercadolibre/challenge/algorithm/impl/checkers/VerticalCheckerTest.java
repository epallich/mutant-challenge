package com.mercadolibre.challenge.algorithm.impl.checkers;

import org.junit.jupiter.api.Test;

import com.mercadolibre.challenge.TestConstants;

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
		assertArrayContainingInAnyOrder(TestConstants.MUTANT_VERTICAL, checker.getLines(TestConstants.MUTANT_DNA));

		log.info("------ Not Mutant DNA on example");
		assertArrayContainingInAnyOrder(TestConstants.NOT_MUTANT_VERTICAL, checker.getLines(TestConstants.NOT_MUTANT_DNA));

		// Expected - Original
		assertArrayContainingInAnyOrder(new String[] { "13", "24" }, checker.getLines(new String[] { "12", "34" }));
		assertArrayContainingInAnyOrder(new String[] { "147", "258", "369" }, checker.getLines(new String[] { "123", "456", "789" }));
		assertArrayContainingInAnyOrder(new String[] { "AEIM", "BFJN", "CGKO", "DHLP" }, checker.getLines(new String[] { "ABCD", "EFGH", "IJKL", "MNOP" }));
		assertArrayContainingInAnyOrder(new String[] { "AFKPU", "BGLQV", "CHMRX", "DINSY", "EJOTZ" }, checker.getLines(new String[] { "ABCDE", "FGHIJ", "KLMNO", "PQRST", "UVXYZ" }));
		// assertArrayContainingInAnyOrder(new String[] { "", "", "", "", "", "" }, checker.getLines(new String[] { "", "", "", "", "", ""
		// }));
	}
}
