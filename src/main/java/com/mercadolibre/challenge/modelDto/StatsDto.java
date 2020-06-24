package com.mercadolibre.challenge.modelDto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatsDto {

	@JsonProperty("count_mutant_dna")
	private Long mutantCount;

	@JsonProperty("count_human_dna")
	private Long humanCount;

	private BigDecimal ratio;

}
