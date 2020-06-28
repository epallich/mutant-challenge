package com.mercadolibre.challenge.algorithm.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mercadolibre.challenge.test.utilities.TestConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MutantFinderTest {

	@Test
	public void isMutantTestTrue() {
		log.info("--- Is Mutant Test True ---");

		log.info("------ Mutant DNA on example");
		Assertions.assertTrue(MutantFinderImpl.INSTANCE.isMutant(TestConstants.MUTANT_DNA));

		// Dos secuencias (ambas diagonales)
		Assertions.assertTrue(MutantFinderImpl.INSTANCE.isMutant(new String[] { "CACA", "CCAT", "GACT", "AGAC" }));

		// Dos secuencias (ambas horizontales)
		Assertions.assertTrue(MutantFinderImpl.INSTANCE.isMutant(new String[] { "ATCG", "TTTT", "ATCG", "GGGG" }));

		// Dos secuencias (ambas verticales)
		Assertions.assertTrue(MutantFinderImpl.INSTANCE.isMutant(new String[] { "ATCG", "ATTG", "ATCG", "AGGG" }));

		// Tres secuencias (El algoritmo encuentra dos y retorna)
		Assertions.assertTrue(MutantFinderImpl.INSTANCE.isMutant(new String[] { "AAAA", "CCCC", "ACAC", "GGGG" }));
	}

	@Test
	public void isMutantTestFalse() {
		log.info("--- Is Mutant Test False (HUMANS)---");

		log.info("------ Not Mutant DNA on example");
		Assertions.assertFalse(MutantFinderImpl.INSTANCE.isMutant(TestConstants.NOT_MUTANT_DNA));

		// Tamaño de array menor a 4x4
		Assertions.assertFalse(MutantFinderImpl.INSTANCE.isMutant(new String[] { "TAC", "CCA", "GGC" }));

		// Ninguna secuencia
		Assertions.assertFalse(MutantFinderImpl.INSTANCE.isMutant(new String[] { "TACA", "CCAT", "GGCT", "AGAT" }));

		// Solo una secuencia
		Assertions.assertFalse(MutantFinderImpl.INSTANCE.isMutant(new String[] { "TACA", "CCAT", "GACT", "AGAT" }));
	}

}
