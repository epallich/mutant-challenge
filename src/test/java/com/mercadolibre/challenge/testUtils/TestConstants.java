package com.mercadolibre.challenge.testUtils;

public final class TestConstants {

	// TODO Lo correcto seria testear todos los casos en ambos sentidos, asi no esta acoplado al algoritmo elegido, sino que mientras haya
	// un match estaria correcto

	public static String[] MUTANT_DNA = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
	public static String[] MUTANT_VERTICAL = { "ACTACT", "TATGCC", "GGAACA", "CTTACC", "GGGGTT", "ACTGAG" };
	public static String[] MUTANT_NEGATIVE_DIAGONAL = { "T", "CC", "ACA", "TGCC", "CTACT", "AAAATG", "TGTGA", "GTGG", "CGT", "GC", "A" };
	public static String[] MUTANT_POSITIVE_DIAGONAL = { "A", "CT", "TAG", "ATGC", "CGATG", "TCATGA", "CCAGC", "ACGT", "CTG", "TA", "G" };

	public static String[] NOT_MUTANT_DNA = { "ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG" };
	public static String[] NOT_MUTANT_VERTICAL = { "ACTAGT", "TATGCC", "GGAAGA", "CTTCTC", "GGTGCT", "ACTGAG" };
	public static String[] NOT_MUTANT_NEGATIVE_DIAGONAL = { "T", "GC", "ACA", "TGGC", "CTATT", "AAACCG", "TGTGA", "GTTG", "CGT", "GC", "A" };
	public static String[] NOT_MUTANT_POSITIVE_DIAGONAL = { "A", "CT", "TAG", "ATGC", "GGATG", "TCATGA", "CGCTC", "ATGT", "CCG", "TA", "G" };

}
