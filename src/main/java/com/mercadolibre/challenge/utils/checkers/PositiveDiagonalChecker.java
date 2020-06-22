package com.mercadolibre.challenge.utils.checkers;

public class PositiveDiagonalChecker extends LineChecker {

	@Override
	protected String[] getLines(String[] dna) {
		int length = dna.length;

		// Inicializando el array de diagonales
		String[] positiveDiagonalLines = new String[length * 2 - 1];
		for (int i = 0; i < positiveDiagonalLines.length; i++) {
			positiveDiagonalLines[i] = "";
		}

		// Lleanando el array con las diagonales
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				String line = dna[j];

				int addition = i + j;
				if (addition < length) {
					if (addition < length - 1) { // Saco la diagonal ppal para no repetirla
						positiveDiagonalLines[addition] += line.charAt(i);
					}

					String line2 = dna[length - 1 - i];

					positiveDiagonalLines[length - 1 + j] += line2.charAt(addition);
				}
			}
		}

		return positiveDiagonalLines;
	}

}
