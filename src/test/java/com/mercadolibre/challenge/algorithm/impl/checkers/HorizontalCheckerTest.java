package com.mercadolibre.challenge.algorithm.impl.checkers;

import org.junit.jupiter.api.Test;

import com.mercadolibre.challenge.TestConstants;
import com.mercadolibre.challenge.algorithm.impl.checkers.HorizontalChecker;
import com.mercadolibre.challenge.algorithm.impl.checkers.AbstractLineChecker;

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
		assertArrayContainingInAnyOrder(TestConstants.MUTANT_DNA, checker.getLines(TestConstants.MUTANT_DNA));

		log.info("------ Not Mutant DNA on example");
		assertArrayContainingInAnyOrder(TestConstants.NOT_MUTANT_DNA, checker.getLines(TestConstants.NOT_MUTANT_DNA));

		assertArrayContainingInAnyOrder(new String[] { "12", "34" }, checker.getLines(new String[] { "12", "34" }));
		assertArrayContainingInAnyOrder(new String[] { "123", "456", "789" }, checker.getLines(new String[] { "123", "456", "789" }));
		assertArrayContainingInAnyOrder(new String[] { "ABCD", "EFGH", "IJKL", "MNOP" }, checker.getLines(new String[] { "ABCD", "EFGH", "IJKL", "MNOP" }));

	}

}
