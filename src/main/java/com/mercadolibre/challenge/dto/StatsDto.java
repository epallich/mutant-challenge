package com.mercadolibre.challenge.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@ToString
@SuppressWarnings("serial")
public class StatsDto implements Serializable {

	@JsonProperty("count_mutant_dna")
	private Long mutantCount;

	@JsonProperty("count_human_dna")
	private Long humanCount;

	private BigDecimal ratio;

	public StatsDto() {
		super();
	}

	public StatsDto(final Long mutantCount, final Long humanCount, final BigDecimal ratio) {
		super();
		this.mutantCount = mutantCount;
		this.humanCount = humanCount;
		this.ratio = ratio;
	}

	public Long getMutantCount() {
		return mutantCount;
	}

	public void setMutantCount(final Long mutantCount) {
		this.mutantCount = mutantCount;
	}

	public Long getHumanCount() {
		return humanCount;
	}

	public void setHumanCount(final Long humanCount) {
		this.humanCount = humanCount;
	}

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(final BigDecimal ratio) {
		this.ratio = ratio;
	}

}
