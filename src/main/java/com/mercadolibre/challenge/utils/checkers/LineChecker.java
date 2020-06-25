package com.mercadolibre.challenge.utils.checkers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class LineChecker {
	private LineChecker next;

	protected static final int MIN_LENGTH = 4;
	protected static final int MIN_SEQUENCES = 2;
	protected static Pattern pattern = Pattern.compile("([A]{4})|([T]{4})|([C]{4})|([G]{4})");

	public LineChecker linkWith(LineChecker next) {
		this.next = next;
		return next;
	}

	protected abstract String[] getLines(String[] dna);

	public boolean checkDnaSequence(String[] dna, int amountOfSequences) {

		// Checking lines
		amountOfSequences = checkLines(getLines(dna), amountOfSequences);

		boolean checkAmount = checkAmount(amountOfSequences);
		if (checkAmount || next == null)
			return checkAmount;

		return next.checkDnaSequence(dna, amountOfSequences);
	}

	protected int checkLines(String[] sequence, int amountOfSequences) {
		int index = 0;
		while (amountOfSequences < MIN_SEQUENCES && index < sequence.length) {
			Matcher matcher = pattern.matcher(sequence[index++]);

			while (amountOfSequences < MIN_SEQUENCES && matcher.find()) {
				log.info("One sequence was found: " + matcher.group());
				amountOfSequences++;
			}
		}

		return amountOfSequences;
	}

	protected boolean checkAmount(int amountOfSequences) {
		return amountOfSequences >= MIN_SEQUENCES;
	}
}
