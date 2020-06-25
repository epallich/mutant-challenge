package com.mercadolibre.challenge.modelDto;

import java.util.stream.Stream;

import org.apache.commons.collections.EnumerationUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import junit.framework.TestResult;
import junit.framework.TestSuite;

public class StatsDtoTest extends AbstractDtoTest<StatsDto> {

	@Test
	public void testStatsDtoClass() {
		TestResult result = new TestResult();

		TestSuite testSuite = getTestSuite(StatsDto.class);
		testSuite.run(result);

		Assertions.assertThat(result.wasSuccessful()).isTrue();
	}
}
