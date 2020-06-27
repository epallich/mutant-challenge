package com.mercadolibre.challenge.algorithm.impl.checkers;

import static com.mercadolibre.challenge.test.utilities.TestConstants.ARRAY_2X2;
import static com.mercadolibre.challenge.test.utilities.TestConstants.ARRAY_3X3;
import static com.mercadolibre.challenge.test.utilities.TestConstants.ARRAY_4X4;
import static com.mercadolibre.challenge.test.utilities.TestConstants.ARRAY_5X5;
import static com.mercadolibre.challenge.test.utilities.TestConstants.ARRAY_7X7;
import static com.mercadolibre.challenge.test.utilities.TestConstants.MUTANT_DNA;
import static com.mercadolibre.challenge.test.utilities.TestConstants.MUTANT_VERTICAL;
import static com.mercadolibre.challenge.test.utilities.TestConstants.NOT_MUTANT_DNA;
import static com.mercadolibre.challenge.test.utilities.TestConstants.NOT_MUTANT_VERTICAL;

import org.junit.jupiter.api.Test;

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

		log.info("------ Mutant DNA on example:");
		assertArrayContainingInAnyOrder(MUTANT_VERTICAL, MUTANT_DNA);

		log.info("------ Not Mutant DNA on example");
		assertArrayContainingInAnyOrder(NOT_MUTANT_VERTICAL, NOT_MUTANT_DNA);

		// Expected - Original
		assertArrayContainingInAnyOrder(new String[] { "AC", "BD" }, ARRAY_2X2);
		assertArrayContainingInAnyOrder(new String[] { "ADG", "BEH", "CFI" }, ARRAY_3X3);
		assertArrayContainingInAnyOrder(new String[] { "AEIM", "BFJN", "CGKO", "DHLP" }, ARRAY_4X4);
		assertArrayContainingInAnyOrder(new String[] { "AFKPU", "BGLQV", "CHMRW", "DINSX", "EJOTY" }, ARRAY_5X5);
		assertArrayContainingInAnyOrder(new String[] { "AHOVCJQ", "BIPWDKR", "CJQXELS", "DKRYFMT", "ELSZGNU", "FMTAHOV", "GNUBIPW" }, ARRAY_7X7);

	}
}
