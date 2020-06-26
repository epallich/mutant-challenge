package com.mercadolibre.challenge;

public final class TestConstants {

	private TestConstants() {
		// Do nothing
	}

	public static String[] MUTANT_DNA = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
	public static String[] MUTANT_VERTICAL = { "ACTACT", "TATGCC", "GGAACA", "CTTACC", "GGGGTT", "ACTGAG" };
	public static String[] MUTANT_NEGATIVE_DIAGONAL = { "TGCC", "CTACT", "AAAATG", "TGTGA", "GTGG" };
	public static String[] MUTANT_POSITIVE_DIAGONAL = { "TGCA", "CGACC", "AGTACT", "GTAGC", "CGTA" };

	public static String[] NOT_MUTANT_DNA = { "ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG" };
	public static String[] NOT_MUTANT_VERTICAL = { "ACTAGT", "TATGCC", "GGAAGA", "CTTCTC", "GGTGCT", "ACTGAG" };
	public static String[] NOT_MUTANT_NEGATIVE_DIAGONAL = { "TGGC", "CTATT", "AAACCG", "TGTGA", "GTTG" };
	public static String[] NOT_MUTANT_POSITIVE_DIAGONAL = { "TGTA", "CTCGC", "AGTACT", "GTAGG", "CGTA" };

}
