package com.mercadolibre.challenge.algorithm.impl.checkers;

public final class NegativeDiagonalChecker extends AbstractDiagonalChecker {

	protected final int getAboveIndex(int length, int offset, int pivot) {
		return ((length + 1) * pivot) + offset;
	}

	protected final int getUnderIndex(int length, int offset, int pivot) {
		return (length + 1) * (pivot + Math.abs(offset)) + offset;
	}

}
