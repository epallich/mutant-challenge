package com.mercadolibre.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.challenge.domain.PersonType;
import com.mercadolibre.challenge.modelDto.MutantDto;
import com.mercadolibre.challenge.modelDto.StatsDto;
import com.mercadolibre.challenge.service.MutantService;
import com.mercadolibre.challenge.validation.OrderedChecks;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MutantController {

	@Autowired
	private MutantService mutantService;

	@RequestMapping(value = "/mutant", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> isMutant(@Validated(OrderedChecks.class) @RequestBody final MutantDto mutantDto) {
		log.info("Is mutant?: {}", mutantDto);

		boolean isMutant = mutantService.isMutant(mutantDto);

		log.info("Result for [{}] = {}", mutantDto.getDna(), PersonType.getPersonType(isMutant));

		if (isMutant) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

	@RequestMapping(value = "/stats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StatsDto> stats() {
		log.info("Getting stats");

		StatsDto statsDto = mutantService.getStats();

		log.info("Returning stats: {}", statsDto);
		return ResponseEntity.ok(statsDto);
	}
}
