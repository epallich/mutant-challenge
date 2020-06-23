package com.mercadolibre.challenge.modelDto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.mercadolibre.challenge.validation.SecondCheck;
import com.mercadolibre.challenge.validation.SquareArray;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MutantDto {

	@NotEmpty
	@SquareArray(groups = SecondCheck.class)
	private List<@NotEmpty @Pattern(regexp = "^[ATCG]*$", message = "Hay algun caracter invalido", groups = SecondCheck.class) String> dna;

}
