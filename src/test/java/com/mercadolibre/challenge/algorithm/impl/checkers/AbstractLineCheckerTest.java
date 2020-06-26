package com.mercadolibre.challenge.algorithm.impl.checkers;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import com.mercadolibre.challenge.algorithm.impl.checkers.AbstractLineChecker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractLineCheckerTest {

	protected abstract void testGetLines();

	protected abstract AbstractLineChecker getLineChecker();

	protected void assertArrayContainingInAnyOrder(String[] expected, String[] actual) {
		log.info("Asserting that the two arrays has the same elements in any order:");
		log.info("expected: [{}] and actual: [{}]", join(expected), join(actual));

		MatcherAssert.assertThat(actual, Matchers.arrayContainingInAnyOrder(expected));
	}

	protected String join(String[] lines) {
		return String.join(",", lines);
	}

}
