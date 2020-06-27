package com.mercadolibre.challenge.algorithm.impl.checkers;

public final class PositiveDiagonalChecker extends AbstractDiagonalChecker {

	protected final int getAboveIndex(int length, int offset, int pivot) {
		return (length - 1) * (pivot + 1) - offset;
	}

	protected final int getUnderIndex(int length, int offset, int pivot) {
		return (length - 1) * (pivot + 1 + Math.abs(offset)) - offset;
	}
}
