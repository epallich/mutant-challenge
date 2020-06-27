package com.mercadolibre.challenge.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mercadolibre.challenge.algorithm.impl.MutantFinder;
import com.mercadolibre.challenge.domain.Person;
import com.mercadolibre.challenge.domain.PersonType;
import com.mercadolibre.challenge.dto.MutantDto;
import com.mercadolibre.challenge.dto.StatsDto;
import com.mercadolibre.challenge.repository.PersonRepository;
import com.mercadolibre.challenge.service.MutantService;
import com.mercadolibre.challenge.utils.GlobalUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MutantServiceImpl implements MutantService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	@Cacheable(value = "mutant_cache", key = "#mutantDto.dna")
	public boolean isMutant(MutantDto mutantDto) {

		final String[] dna = mutantDto.getDna().toArray(new String[0]);

		boolean isMutant = MutantFinder.INSTANCE.isMutant(dna);

		savePerson(dna, isMutant);

		return isMutant;
	}

	private void savePerson(final String[] dna, boolean isMutant) {
		Person person = new Person(PersonType.getPersonType(isMutant), GlobalUtils.join(dna));
		log.info("Saving new Person: [{}]", person);
		personRepository.save(person);
	}

	@Override
	public StatsDto getStats() {
		Long mutantCount = personRepository.countByType(PersonType.MUTANT.name());
		Long normalCount = personRepository.countByType(PersonType.NORMAL.name());

		return toStatDto(mutantCount, mutantCount + normalCount);
	}

	private StatsDto toStatDto(Long mutantCount, Long humanCount) {
		return new StatsDto(mutantCount, humanCount, getRatio(mutantCount, humanCount));
	}

	/**
	 * Returns the calculated ratio
	 * Mutant count / Human count
	 * -- 0 / 0 -> 0
	 * -- x / y -> the division rounded
	 * @param mutantCount
	 * @param humanCount
	 * @return
	 */
	private BigDecimal getRatio(Long mutantCount, Long humanCount) {
		BigDecimal ratio = BigDecimal.ZERO;
		if (humanCount > 0) {
			ratio = BigDecimal.valueOf(mutantCount).divide(BigDecimal.valueOf(humanCount), 2, RoundingMode.HALF_UP);
		}
		return ratio;
	}

}
