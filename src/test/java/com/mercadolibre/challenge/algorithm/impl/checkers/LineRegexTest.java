package com.mercadolibre.challenge.algorithm.impl.checkers;

import java.util.regex.Matcher;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LineRegexTest {

	private static final String AAAA = "AAAA";
	private static final String TTTT = "TTTT";
	private static final String CCCC = "CCCC";
	private static final String GGGG = "GGGG";

	@Test
	public void testRegex() {

		// Not match
		assertNotFind("ATGCATGCATGC");

		// Match 1
		assertFindNtimes("ATGCAAAATGC", AAAA, 1);
		assertFindNtimes("ATGCTTTTTGC", TTTT, 1);
		assertFindNtimes("ATCCCCAATGC", CCCC, 1);
		assertFindNtimes("ATGCGGGGTGC", GGGG, 1);

		// Match 2 contiguous
		assertFindNtimes("ATGCAAAAAAAATGC", AAAA, 2);
		assertFindNtimes("ATGCTTTTTTTTTGC", TTTT, 2);
		assertFindNtimes("ATGCCCCCCCCCTGC", CCCC, 2);
		assertFindNtimes("ATGCGGGGGGGGTGC", GGGG, 2);

		// Match 3
		assertFindNtimes("AAAATGCAAAATGAAAAC", AAAA, 3);
		assertFindNtimes("TTTTTGCTTTTTGTTTTC", TTTT, 3);
		assertFindNtimes("CCCCTGCCCCCTGCCCCC", CCCC, 3);
		assertFindNtimes("GGGGTGCGGGGTGGGGGC", GGGG, 3);

		assertFindMixed("CCCCTAAAA", CCCC, AAAA);
		assertFindMixed("TTTTCCACCTGGGG", TTTT, GGGG);
		assertFindMixed("TTTTCCACCTAAAACCACCCCTTGGGG", TTTT, AAAA, CCCC, GGGG);
	}

	private void assertNotFind(String sequence) {
		Matcher matcher = getMatcher(sequence);
		Assertions.assertFalse(matcher.find());
	}

	private void assertFindNtimes(String sequence, String group, int count) {
		Matcher matcher = getMatcher(sequence);

		for (int i = 0; i < count; i++) {
			Assertions.assertTrue(matcher.find());
			Assertions.assertEquals(group, matcher.group());
		}

		Assertions.assertFalse(matcher.find());
	}

	private void assertFindMixed(String sequence, String... groups) {
		Matcher matcher = getMatcher(sequence);

		for (String group : groups) {
			Assertions.assertTrue(matcher.find());
			Assertions.assertEquals(group, matcher.group());
		}

		Assertions.assertFalse(matcher.find());
	}

	private Matcher getMatcher(String sequence) {
		return AbstractLineChecker.pattern.matcher(sequence);
	}
}
