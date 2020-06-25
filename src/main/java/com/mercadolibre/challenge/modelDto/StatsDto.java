package com.mercadolibre.challenge.modelDto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@SuppressWarnings("serial")
public class StatsDto implements Serializable {

	@JsonProperty("count_mutant_dna")
	private long mutantCount;

	@JsonProperty("count_human_dna")
	private long humanCount;

	private double ratio;

	public StatsDto() {
		super();
	}

	public StatsDto(final long mutantCount, final long humanCount, final double ratio) {
		super();
		this.mutantCount = mutantCount;
		this.humanCount = humanCount;
		this.ratio = ratio;
	}

	public long getMutantCount() {
		return mutantCount;
	}

	public void setMutantCount(final long mutantCount) {
		this.mutantCount = mutantCount;
	}

	public long getHumanCount() {
		return humanCount;
	}

	public void setHumanCount(final long humanCount) {
		this.humanCount = humanCount;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(final double ratio) {
		this.ratio = ratio;
	}

}
