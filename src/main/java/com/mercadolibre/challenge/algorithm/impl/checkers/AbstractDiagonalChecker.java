package com.mercadolibre.challenge.algorithm.impl.checkers;

import java.util.List;

import com.google.common.collect.Lists;
import com.mercadolibre.challenge.utils.GlobalUtils;

public abstract class AbstractDiagonalChecker extends AbstractLineChecker {

	/**
	 * @return Retorna el indice dentro de la secuencia concatenada de adn del elemento [i][x]
	 */
	protected abstract int getAboveIndex(int length, int i, int x);

	protected abstract int getUnderIndex(int length, int i, int x);

	@Override
	protected final String[] getLines(String[] dnaArray) {
		int length = dnaArray.length;

		if (length < MIN_LENGTH) {
			return new String[0];
		}

		final String dnaSequence = GlobalUtils.join(dnaArray);
		final int diagonalsAbovePrincipal = (length - MIN_LENGTH);
		final List<String> diagonalLines = Lists.newArrayList();

		for (int i = -diagonalsAbovePrincipal; i <= diagonalsAbovePrincipal; i++) {
			if (i >= 0) {
				getAbovePrincipalDiagonal(length, dnaSequence, i, diagonalLines);
			} else {
				getUnderPrincipalDiagonal(length, dnaSequence, i, diagonalLines);
			}
		}

		return diagonalLines.toArray(new String[0]);
	}

	private void getAbovePrincipalDiagonal(int length, String dnaSequence, int i, List<String> diagonals) {
		String diagonal = "";

		int x = 0;
		while (getAboveIndex(length, i, x) < dnaSequence.length() && checkDiagonalLength(length, i, x)) {
			diagonal += dnaSequence.charAt(getAboveIndex(length, i, x));
			x++;
		}

		diagonals.add(diagonal);
	}

	private void getUnderPrincipalDiagonal(int length, String dnaSequence, int i, List<String> diagonals) {
		String diagonal = "";

		int x = 0;
		while (getUnderIndex(length, i, x) < dnaSequence.length() && checkDiagonalLength(length, i, x)) {
			diagonal += dnaSequence.charAt(getUnderIndex(length, i, x));
			x++;
		}

		diagonals.add(diagonal);
	}

	/**
	 * Checks the lenth of the new diagonal in order to take the exact number of elements from the dnaSequence
	 * @param length
	 * The length of the line
	 * @param i
	 * The diagonal index (-n,n)
	 * @param x
	 * The actual element index of the new line
	 * @return
	 */
	private boolean checkDiagonalLength(int length, int i, int x) {
		return x < length - Math.abs(i);
	}
}
