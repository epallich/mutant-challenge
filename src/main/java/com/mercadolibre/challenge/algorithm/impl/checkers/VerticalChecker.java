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

		for (int offset = 0; offset < length; offset++) {
			StringBuilder diagonal = new StringBuilder();

			for (int pivot = 0; pivot < length; pivot++) {
				diagonal.append(dnaSequence.charAt(getIndex(length, offset, pivot)));
			}
			verticalLines.add(diagonal.toString());
		}

		return verticalLines.toArray(new String[0]);
	}

	/**
	 * Calculate and return the element[pivot] of the new line
	 * @param length
	 * The length of the line
	 * @param offset
	 * The vertical index (0,N)
	 * @param pivot
	 * The actual element index of the new line
	 * @return
	 */
	private int getIndex(int length, int offset, int pivot) {
		return (length * pivot) + offset;
	}

}
