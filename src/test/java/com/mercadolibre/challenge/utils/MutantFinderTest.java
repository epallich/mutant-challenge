package com.mercadolibre.challenge.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mercadolibre.challenge.testUtils.TestConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MutantFinderTest {

	@Test
	public void isMutantTest() {
		log.info("--- Is Mutant Test ---");

		log.info("------ Mutant DNA");
		Assertions.assertTrue(MutantFinder.INSTANCE.isMutant(TestConstants.MUTANT_DNA));

		log.info("------ Not Mutant DNA");
		Assertions.assertFalse(MutantFinder.INSTANCE.isMutant(TestConstants.NOT_MUTANT_DNA));
	}

}
