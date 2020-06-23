package com.mercadolibre.challenge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.challenge.modelDto.MutantDto;
import com.mercadolibre.challenge.utils.MutantFinder;
import com.mercadolibre.challenge.validation.OrderedChecks;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MutantController {

	@RequestMapping(value = "/mutant", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> isMutant(@Validated(OrderedChecks.class) @RequestBody MutantDto mutant) {
		log.info("Is mutant?: {}", mutant);

		// TODO tomar de cache, si esta en cache evitar llamada a isMutant

		final String[] dna = mutant.getDna().toArray(new String[0]);
		boolean isMutant = MutantFinder.INSTANCE.isMutant(dna);

		// TODO guardar resultado en base

		if (isMutant) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

	// TODO /stats
}
