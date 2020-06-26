package com.mercadolibre.challenge;

public final class TestConstants {

	private TestConstants() {
		// Do nothing
	}

	public static final String[] MUTANT_DNA = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
	public static final String[] MUTANT_VERTICAL = { "ACTACT", "TATGCC", "GGAACA", "CTTACC", "GGGGTT", "ACTGAG" };
	public static final String[] MUTANT_NEGATIVE_DIAGONAL = { "TGCC", "CTACT", "AAAATG", "TGTGA", "GTGG" };
	public static final String[] MUTANT_POSITIVE_DIAGONAL = { "TGCA", "CGACC", "AGTACT", "GTAGC", "CGTA" };

	public static final String[] NOT_MUTANT_DNA = { "ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG" };
	public static final String[] NOT_MUTANT_VERTICAL = { "ACTAGT", "TATGCC", "GGAAGA", "CTTCTC", "GGTGCT", "ACTGAG" };
	public static final String[] NOT_MUTANT_NEGATIVE_DIAGONAL = { "TGGC", "CTATT", "AAACCG", "TGTGA", "GTTG" };
	public static final String[] NOT_MUTANT_POSITIVE_DIAGONAL = { "TGTA", "CTCGC", "AGTACT", "GTAGG", "CGTA" };

	public static final String[] ARRAY_2X2 = { "AB", "CD" };
	public static final String[] ARRAY_3X3 = { "ABC", "DEF", "GHI" };
	public static final String[] ARRAY_4X4 = { "ABCD", "EFGH", "IJKL", "MNOP" };
	public static final String[] ARRAY_5X5 = { "ABCDE", "FGHIJ", "KLMNO", "PQRST", "UVWXY" };
	public static final String[] ARRAY_7X7 = { "ABCDEFG", "HIJKLMN", "OPQRSTU", "VWXYZAB", "CDEFGHI", "JKLMNOP", "QRSTUVW" };
}
