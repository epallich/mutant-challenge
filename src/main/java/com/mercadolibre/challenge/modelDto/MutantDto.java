package com.mercadolibre.challenge.modelDto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class MutantDto {

	// TODO testear
	// vs String[] ?

	// TODO validar n*n
	// @CuadraticArray
	@NotNull
	@NotEmpty
	private List<@NotNull @Pattern(regexp = "^[ATCG]*$", message = "Hay algun caracter invalido") String> dna;

	/*
	 * TODO Casos de prueba:
	 * 1. dna: null -> not null
	 * 2: dna: [] -> not empty
	 * 3: dna: ["AA"] -> no es matriz cuadrada
	 * 4: dna: ["ATCG","GTAT","TTAC", "ZZZZ"] -> caracter invalido
	 * 5. dna: ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] -> OK
	 * El size no me interesa mientras sea cuadrada y tenga caracteres validos
	 */
}
