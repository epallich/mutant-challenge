package com.mercadolibre.challenge.utils.checkers;

import java.util.List;

import com.google.common.collect.Lists;

public class PositiveDiagonalChecker extends LineChecker {

	@Override
	protected String[] getLines(String[] dnaArray) {
		int length = dnaArray.length;

		if (length < MIN_LENGTH) {
			return new String[0];
		}

		int diagonalsAbovePrincipal = (length - MIN_LENGTH);

		// TODO Global.join
		String dnaSequence = String.join("", dnaArray);

		List<String> positiveDiagonalLines = Lists.newArrayList();
		for (int i = 0 - diagonalsAbovePrincipal; i <= diagonalsAbovePrincipal; i++) {
			if (i <= 0) {// principal or above
				getAbovePrincipalDiagonal(length, dnaSequence, i, positiveDiagonalLines);
			} else {
				getUnderPrincipalDiagonal(length, dnaSequence, i, positiveDiagonalLines);
			}
		}

		return positiveDiagonalLines.toArray(new String[0]);
	}

	private void getAbovePrincipalDiagonal(int length, String dnaSequence, int i, List<String> positiveDiagonalLines) {
		String diagonal = "";

		int x = 0;
		while (getAboveIndex(length, i, x) < dnaSequence.length() && x < length + i) {
			diagonal += dnaSequence.charAt(getAboveIndex(length, i, x));
			x++;
		}

		positiveDiagonalLines.add(diagonal);
	}

	/**
	 * @return Retorna el indice dentro de la secuencia concatenada de adn del elemento [i][x]
	 */
	private int getAboveIndex(int length, int i, int x) {
		// Empieza en el elemento[0] del array original
		// Toma el primer caracter de la diagonal con la minimo de 4 elementos
		// Matriz de 4x4 = 4-1 + (0) + (0 *(4-1)) = 3
		// Matriz de 5x5 = 5-1 + (-1) + (0 *(5-1)) = 3

		return ((length - 1) + i) + (x * (length - 1));
	}

	private void getUnderPrincipalDiagonal(int length, String dnaSequence, int i, List<String> positiveDiagonalLines) {
		String diagonal = "";

		int x = 0;
		while (getUnderIndex(length, i, x) < dnaSequence.length() && x < length - i) {
			diagonal += dnaSequence.charAt(getUnderIndex(length, i, x));
			x++;
		}

		positiveDiagonalLines.add(diagonal);
	}

	private int getUnderIndex(int length, int i, int x) {
		return (length * i) + (5 * (1 + x));
	}
}
