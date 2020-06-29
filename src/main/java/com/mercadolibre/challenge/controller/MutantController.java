package com.mercadolibre.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.challenge.domain.PersonType;
import com.mercadolibre.challenge.dto.MutantDto;
import com.mercadolibre.challenge.dto.StatsDto;
import com.mercadolibre.challenge.service.MutantService;
import com.mercadolibre.challenge.validation.OrderedChecks;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MutantController {

	@Autowired
	private MutantService mutantService;

	/**
	 * Checks the dna, save and return the result
	 * @param mutantDto
	 * The mutant dto with the dna to check
	 * @return
	 * [200-OK] -- if the dna belongs to a mutant.<br>
	 * [403-FORBIDDEN] -- if the dna belongs to a normal human.<br>
	 * [400-BAD_REQUEST] -- if the mutant dto has some validation errors.<br>
	 */
	//@formatter:off
	@ApiOperation(value = "Checks the given dna", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "if the dna belongs to a mutant"),
            @ApiResponse(code = 403, message = "if the dna belongs to a normal human"),
            @ApiResponse(code = 400, message = "if the mutant dto has some validation errors")
    	}
    )
	@PostMapping(value = "/mutant", consumes = MediaType.APPLICATION_JSON_VALUE)
	//@formatter:on
	public ResponseEntity<Void> isMutant(@Validated(OrderedChecks.class) @RequestBody final MutantDto mutantDto) {
		log.info("Is mutant?: {}", mutantDto);

		boolean isMutant = mutantService.isMutant(mutantDto);

		log.info("Result for [{}] = {}", mutantDto.getDna(), PersonType.getPersonType(isMutant));

		if (isMutant) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

	/**
	 * Get the statistical data from the evaluated dnas<br>
	 * The number of mutants found<br>
	 * The number of humans evaluated<br>
	 * The ratio of mutants
	 * @return
	 * [200-OK] with the StatsDto data in the response body
	 */
	//@formatter:off
	@ApiOperation(value = "Get the statistical data from the evaluated dnas", response = StatsDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a json with the amount of mutans, the total of evaluated humans and the ratio")
    	}
    )
	@GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
	//@formatter:on
	public ResponseEntity<StatsDto> stats() {
		log.info("Getting stats");

		StatsDto statsDto = mutantService.getStats();

		log.info("Returning stats: {}", statsDto);
		return ResponseEntity.ok(statsDto);
	}
}
