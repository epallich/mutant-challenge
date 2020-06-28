package com.mercadolibre.challenge.service;

import com.mercadolibre.challenge.dto.MutantDto;
import com.mercadolibre.challenge.dto.StatsDto;

public interface MutantService {

	/**
	 * Evaluates the mutant condition and save the result
	 * @param mutantDto
	 * The mutant dto, with the dna sequence data
	 * @return
	 * true if the dna belongs to a mutant or false if not
	 */
	public boolean isMutant(final MutantDto mutantDto);

	/**
	 * Get the statistical data from the evaluated dnas<br>
	 * The number of mutants found<br>
	 * The number of humans evaluated<br>
	 * The ratio of mutants
	 * @return
	 * Returns the stats dto
	 */
	public StatsDto getStats();
}
