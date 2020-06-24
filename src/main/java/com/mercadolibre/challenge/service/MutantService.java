package com.mercadolibre.challenge.service;

import com.mercadolibre.challenge.modelDto.MutantDto;
import com.mercadolibre.challenge.modelDto.StatsDto;

public interface MutantService {

	public boolean isMutant(final MutantDto mutantDto);

	public StatsDto getStats();
}
