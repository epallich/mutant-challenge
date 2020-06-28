package com.mercadolibre.challenge.algorithm.impl.checkers;

import static com.mercadolibre.challenge.ApplicationConstants.MIN_SEQUENCES;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * The Abstract checker
 * Find the lines and then check them with the pattern
 * @author epallich
 */

@Slf4j
public abstract class AbstractLineChecker {
	private AbstractLineChecker next;

	protected static final Pattern pattern = Pattern.compile("([A]{4})|([T]{4})|([C]{4})|([G]{4})");

	public AbstractLineChecker linkWith(AbstractLineChecker next) {
		this.next = next;
		return next;
	}

	/**
	 * Get the lines of the new perspective
	 * @param dna
	 * The dna array
	 * @return
	 * the new lines
	 */
	protected abstract String[] getLines(String[] dna);

	/**
	 * Checks the lines in a particular way <br>
	 * If the minimum amount of sequences is reached, returns true <br>
	 * If not, delegates the task to the next checker <br>
	 * If no more checkers, returns false <br>
	 * @param dna
	 * The dna array
	 * @param amountOfSequences
	 * The amount of sequences found until this moment (starts in 0)
	 * @return
	 * true if the dna belongs to a mutant or false if not
	 */
	public boolean checkDnaSequence(final String[] dna, int amountOfSequences) {

		// Checking lines
		amountOfSequences = checkLines(getLines(dna.clone()), amountOfSequences);

		boolean checkAmount = checkAmount(amountOfSequences);
		if (checkAmount || next == null)
			return checkAmount;

		return next.checkDnaSequence(dna, amountOfSequences);
	}

	/**
	 * Checks the lines with the Pattern and return the amount of sequences found <br>
	 * If the amount is equals to the MIN_SEQUENCES returns that value
	 * @param sequence
	 * The sequence array to check
	 * @param amountOfSequences
	 * The amount of sequences found until this moment
	 * @return
	 * The number of sequences passed as a parameter added to the sequences found in this method
	 */
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

	private boolean checkAmount(int amountOfSequences) {
		return amountOfSequences >= MIN_SEQUENCES;
	}
}
