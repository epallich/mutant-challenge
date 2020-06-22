package com.mercadolibre.challenge.utils.checkers;

public class VerticalChecker extends LineChecker {

	@Override
	protected String[] getLines(String[] dna) {
		String[] verticalLines = new String[dna.length];

		for (int i = 0; i < dna.length; i++) {

			String line = dna[i];
			for (int j = 0; j < line.length(); j++) {
				if (verticalLines[j] == null)
					verticalLines[j] = "";

				verticalLines[j] += line.charAt(j);
			}
		}

		return verticalLines;
	}

}
