package com.mercadolibre.challenge.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.challenge.modelDto.MutantDto;
import com.mercadolibre.challenge.utils.MutantFinder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MutantController {

	@RequestMapping(value = "/mutant", method = RequestMethod.POST)
	public ResponseEntity<Void> isMutant(@Valid @RequestBody MutantDto mutant) {
		log.info("Is mutant?: {}", mutant);

		// TODO tomar de cache, si esta en cache evitar llamada a isMutant

		boolean isMutant = MutantFinder.INSTANCE.isMutant(mutant.getDna().toArray(new String[0]));

		// TODO guardar resultado en base

		if (isMutant) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

}
