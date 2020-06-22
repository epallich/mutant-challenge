package com.mercadolibre.challenge.utils.checkers;

public class NegativeDiagonalChecker extends LineChecker {

	@Override
	protected String[] getLines(String[] dna) {
		int length = dna.length;

		// Inicializando el array de diagonales
		String[] negativeDiagonalLines = new String[length * 2 - 1];
		for (int i = 0; i < negativeDiagonalLines.length; i++) {
			negativeDiagonalLines[i] = "";
		}

		// Lleanando el array con las diagonales
		for (int i = 0; i < length; i++) {
			String line = dna[i];
			for (int j = 0; j < length; j++) {
				int addition = i + j;
				if (addition < length) {
					if (j > 0) {// No tomo la diagonal ppal para no duplicarla
						negativeDiagonalLines[j - 1] += line.charAt(addition);
					}

					String line2 = dna[addition];
					negativeDiagonalLines[length - 1 + j] += line2.charAt(i);
				}
			}
		}

		return negativeDiagonalLines;
	}

}
