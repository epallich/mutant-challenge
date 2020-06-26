package com.mercadolibre.challenge.algorithm.impl.checkers;

public final class NegativeDiagonalChecker extends AbstractDiagonalChecker {

	protected final int getAboveIndex(int length, int i, int x) {
		return ((length + 1) * x) + i;
	}

	protected final int getUnderIndex(int length, int i, int x) {
		return (length + 1) * (x + Math.abs(i)) + i;
	}

}
