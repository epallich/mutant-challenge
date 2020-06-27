package com.mercadolibre.challenge.algorithm;

/**
 * @author epallich
 */
public interface MutantFinderInterface {

	/**
	 * Returns if a human is mutant.
	 * if the dna has more than one sequence of four equal letters, obliquely, horizontally or vertically.
	 * @param dna
	 * @return
	 */
	public boolean isMutant(String[] dna);
}
