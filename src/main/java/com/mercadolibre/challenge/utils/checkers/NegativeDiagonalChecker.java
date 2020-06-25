package com.mercadolibre.challenge.utils.checkers;

import java.util.List;

import com.google.common.collect.Lists;

public class NegativeDiagonalChecker extends LineChecker {

	@Override
	protected String[] getLines(String[] dnaArray) {
		int length = dnaArray.length;

		if (length < MIN_LENGTH) {
			return new String[0];
		}

		int diagonalsAbovePrincipal = (length - MIN_LENGTH);

		String dnaSequence = String.join("", dnaArray);

		List<String> negativeDiagonalLines = Lists.newArrayList();
		for (int i = 0 - diagonalsAbovePrincipal; i <= diagonalsAbovePrincipal; i++) {
			if (i < 0) {
				getUnderPrincipalDiagonal(length, dnaSequence, i, negativeDiagonalLines);
			} else { // principal or above
				getAbovePrincipalDiagonal(length, dnaSequence, i, negativeDiagonalLines);
			}
		}

		return negativeDiagonalLines.toArray(new String[0]);
	}

	private void getAbovePrincipalDiagonal(int length, String dnaSequence, int i, List<String> negativeDiagonalLines) {
		String diagonal = "";

		int x = 0;
		while (getAboveIndex(length, i, x) < dnaSequence.length() && x < length - i) {
			diagonal += dnaSequence.charAt(getAboveIndex(length, i, x));
			x++;
		}

		negativeDiagonalLines.add(diagonal);
	}

	private int getAboveIndex(int length, int i, int x) {
		return i + x + (x * length);
	}

	private void getUnderPrincipalDiagonal(int length, String dnaSequence, int i, List<String> negativeDiagonalLines) {
		String diagonal = "";

		int x = 0;
		while (getIUnderIndex(length, i, x) < dnaSequence.length() && x < length + i) {
			diagonal += dnaSequence.charAt(getIUnderIndex(length, i, x));
			x++;
		}

		negativeDiagonalLines.add(diagonal);
	}

	private int getIUnderIndex(int length, int i, int x) {
		return ((Math.abs(i) + x) * length) + x;
	}

}
