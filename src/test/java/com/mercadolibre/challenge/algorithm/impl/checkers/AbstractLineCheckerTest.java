package com.mercadolibre.challenge.algorithm.impl.checkers;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractLineCheckerTest {

	protected abstract void testGetLines();

	protected abstract AbstractLineChecker getLineChecker();

	protected void assertArrayContainingInAnyOrder(String[] expected, String[] dna) {
		printMatrix(dna);

		AbstractLineChecker checker = getLineChecker();
		String[] actual = checker.getLines(dna);

		log.info("Asserting that the two arrays has the same elements in any order:");
		log.info("expected: [{}] and actual: [{}]", join(expected), join(actual));

		MatcherAssert.assertThat(actual, Matchers.arrayContainingInAnyOrder(expected));
	}

	protected String join(String[] lines) {
		return String.join(",", lines);
	}

	protected void printMatrix(final String[] dna) {
		final StringBuilder sb = new StringBuilder();
		for (int element = 0; element < dna.length; element++) {
			sb.append("\n");
			final String elementString = dna[element];
			for (int index = 0; index < elementString.length(); index++) {
				sb.append(elementString.charAt(index)).append(" ");
			}
		}
		log.info("Printing matrix to test:\n{}\n", sb.toString());
	}

}
