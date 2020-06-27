package com.mercadolibre.challenge.modelDto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.mercadolibre.challenge.utils.GlobalUtils;
import com.mercadolibre.challenge.validation.SecondCheck;
import com.mercadolibre.challenge.validation.SquareArray;

import lombok.ToString;

@ToString
@SuppressWarnings("serial")
public class MutantDto implements Serializable {

	@NotEmpty
	@SquareArray(groups = SecondCheck.class)
	private List<@NotEmpty @Pattern(regexp = "^[ATCG]*$", message = "There is some invalid character.", groups = SecondCheck.class) String> dna;

	public MutantDto() {
		super();
	}

	public MutantDto(final List<String> dna) {
		super();
		setDna(dna);
	}

	public List<String> getDna() {
		this.dna = GlobalUtils.nullSafety(this.dna);
		return dna;
	}

	public void setDna(final List<String> dna) {
		this.dna = GlobalUtils.cloneNullSafety(dna);
	}

}
