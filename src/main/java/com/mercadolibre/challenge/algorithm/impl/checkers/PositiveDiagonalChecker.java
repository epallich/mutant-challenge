package com.mercadolibre.challenge.algorithm.impl.checkers;

public final class PositiveDiagonalChecker extends AbstractDiagonalChecker {

	protected final int getAboveIndex(int length, int i, int x) {
		return (length - 1) * (x + 1) - i;
	}

	protected final int getUnderIndex(int length, int i, int x) {
		return (length - 1) * (x + 1 + Math.abs(i)) - i;
	}
}
