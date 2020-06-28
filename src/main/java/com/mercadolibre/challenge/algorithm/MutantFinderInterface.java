package com.mercadolibre.challenge.algorithm;

/**
 * The algorithm to check if a dna belongs to a mutant must implement this interface
 * @author epallich
 */
public interface MutantFinderInterface {

	/**
	 * Returns if a human is mutant.
	 * if the dna has more than one sequence of four equal letters, obliquely, horizontally or vertically.
	 * @param dna
	 * The dna array
	 * @return
	 * true if the dna belongs to a mutant or false if not
	 */
	public boolean isMutant(String[] dna);
}
