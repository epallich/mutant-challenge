package com.mercadolibre.challenge.service;

import com.mercadolibre.challenge.modelDto.MutantDto;
import com.mercadolibre.challenge.modelDto.StatsDto;

public interface MutantService {

	/**
	 * Evaluates the mutant condition and save the result
	 * @param mutantDto
	 * @return
	 */
	public boolean isMutant(final MutantDto mutantDto);

	/**
	 * Returns statistical data from the evaluated dnas
	 * @return
	 */
	public StatsDto getStats();
}
