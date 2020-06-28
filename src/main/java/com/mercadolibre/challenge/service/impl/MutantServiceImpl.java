package com.mercadolibre.challenge.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mercadolibre.challenge.algorithm.impl.MutantFinderImpl;
import com.mercadolibre.challenge.domain.Person;
import com.mercadolibre.challenge.domain.PersonType;
import com.mercadolibre.challenge.dto.MutantDto;
import com.mercadolibre.challenge.dto.StatsDto;
import com.mercadolibre.challenge.repository.PersonRepository;
import com.mercadolibre.challenge.service.MutantService;
import com.mercadolibre.challenge.utils.GlobalUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * The implementation of the MutantService interface
 * @author epallich
 */
@Slf4j
@Service
public class MutantServiceImpl implements MutantService {

	@Autowired
	private PersonRepository personRepository;

	/**
	 * This method has a fixed size cache<br>
	 * If the dna was previously checked and exists in the cache, all the processing is avoided and returns the previous result<br>
	 * If not exists in the cache, the dna is processed and saved the result
	 */
	@Override
	@Cacheable(value = "mutant_cache", key = "#mutantDto.dna")
	public boolean isMutant(MutantDto mutantDto) {

		final String[] dna = mutantDto.getDna().toArray(new String[0]);

		boolean isMutant = MutantFinderImpl.INSTANCE.isMutant(dna);

		savePerson(dna, isMutant);

		return isMutant;
	}

	/**
	 * This method save the person.
	 * @param dna
	 * the dna sequence of the person
	 * @param isMutant
	 * the processed result
	 */
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
	 * Returns the calculated ratio <br>
	 * Mutant count / Human count <br>
	 * -- 0 / 0 == 0 <br>
	 * -- x / y == the division rounded <br>
	 * @param mutantCount
	 * The mutant count
	 * @param humanCount
	 * The total human count (including the mutants)
	 * @return
	 * The calculated mutant ratio
	 */
	private BigDecimal getRatio(Long mutantCount, Long humanCount) {
		BigDecimal ratio = BigDecimal.ZERO;
		if (humanCount > 0) {
			ratio = BigDecimal.valueOf(mutantCount).divide(BigDecimal.valueOf(humanCount), 2, RoundingMode.HALF_UP);
		}
		return ratio;
	}

}
