package com.mercadolibre.challenge.algorithm.impl.checkers;

import java.util.List;

import com.google.common.collect.Lists;
import com.mercadolibre.challenge.utils.GlobalUtils;

public final class VerticalChecker extends AbstractLineChecker {

	@Override
	protected final String[] getLines(String[] dnaArray) {
		int length = dnaArray.length;

		final String dnaSequence = GlobalUtils.join(dnaArray);
		final List<String> verticalLines = Lists.newArrayList();

		for (int i = 0; i < length; i++) {

			String line = "";
			for (int x = 0; x < length; x++) {
				line += dnaSequence.charAt(getIndex(length, i, x));
			}
			verticalLines.add(line);
		}

		return verticalLines.toArray(new String[0]);
	}

	private int getIndex(int length, int i, int x) {
		return (length * x) + i;
	}

}
