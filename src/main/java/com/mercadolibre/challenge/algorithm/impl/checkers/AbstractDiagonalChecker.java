package com.mercadolibre.challenge.algorithm.impl.checkers;

import static com.mercadolibre.challenge.ApplicationConstants.MIN_LENGTH;

import java.util.List;

import com.google.common.collect.Lists;
import com.mercadolibre.challenge.utils.GlobalUtils;

public abstract class AbstractDiagonalChecker extends AbstractLineChecker {

	/**
	 * This operator is used to choose the index method based on the offset in
	 * {@link AbstractDiagonalChecker#getDiagonalLine(int, String, int)
	 * getDiagonalLine} <br>
	 * If the offset is positive or zero, uses {@link AbstractDiagonalChecker#getAboveIndex(int, int, int) getAboveIndex}<br>
	 * If the offset is negative, uses {@link AbstractDiagonalChecker#getUnderIndex(int, int, int) getUnderIndex}<br>
	 */
	private interface IndexOperator {
		/**
		 * Calculate and return the element[pivot] of the new line
		 * @param pivot
		 * The actual element index of the new line
		 * @return
		 * The calculated index
		 */
		public int getIndex(int pivot);
	}

	/**
	 * Calculate and return the element[pivot] of the new line
	 * @param length
	 * The length of the line
	 * @param offset
	 * The diagonal index (4-N,N-4), at this moment the offset is positive or zero
	 * @param pivot
	 * The actual element index of the new line
	 * @return
	 * The calculated index
	 */
	protected abstract int getAboveIndex(int length, int offset, int pivot);

	/**
	 * Calculate and return the element[pivot] of the new line
	 * @param length
	 * The length of the line
	 * @param offset
	 * The diagonal index (4-N,N-4), at this moment the offset is always negative
	 * @param pivot
	 * The actual element index of the new line
	 * @return
	 * The calculated index
	 */
	protected abstract int getUnderIndex(int length, int offset, int pivot);

	/**
	 * Returns the diagonal lines
	 */
	@Override
	protected final String[] getLines(String[] dnaArray) {
		int length = dnaArray.length;

		final String dnaSequence = GlobalUtils.join(dnaArray);
		final int diagonalsAbovePrincipal = (length - MIN_LENGTH);
		final List<String> diagonalLines = Lists.newArrayList();

		for (int offset = -diagonalsAbovePrincipal; offset <= diagonalsAbovePrincipal; offset++) {
			diagonalLines.add(getDiagonalLine(length, dnaSequence, offset));
		}

		return diagonalLines.toArray(new String[0]);
	}

	/**
	 * @param length
	 * The length of the dna array (N)
	 * @param dnaSequence
	 * The concatenated dna sequence
	 * @param offset
	 * The diagonal index (4-N,N-4)
	 * @return
	 * The diagonal line
	 */
	private String getDiagonalLine(int length, String dnaSequence, int offset) {
		StringBuilder diagonal = new StringBuilder();

		// If the offset is positive or zero, call the getAboveIndex
		// otherwise call the getUnderIndex
		IndexOperator indexOperator = p -> offset >= 0 ? this.getAboveIndex(length, offset, p) : this.getUnderIndex(length, offset, p);

		int pivot = 0;
		int index = 0;
		while ((index = indexOperator.getIndex(pivot)) < dnaSequence.length() && checkDiagonalLength(length, offset, pivot)) {
			diagonal.append(dnaSequence.charAt(index));
			pivot++;
		}

		return diagonal.toString();
	}

	/**
	 * Checks the length of the new diagonal in order to take the exact number of elements from the dnaSequence
	 * @param length
	 * The length of the line
	 * @param offset
	 * The diagonal index (4-N,N-4)
	 * @param pivot
	 * The actual element index of the new line
	 * @return
	 * true if the pivot element belongs to the offset line
	 */
	private boolean checkDiagonalLength(int length, int offset, int pivot) {
		return pivot < length - Math.abs(offset);
	}
}
