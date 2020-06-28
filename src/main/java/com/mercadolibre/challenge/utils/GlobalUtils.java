package com.mercadolibre.challenge.utils;

import java.util.List;

import com.google.common.collect.Lists;

public final class GlobalUtils {

	/**
	 * Private Constructor
	 */
	private GlobalUtils() {
		// Do nothing...
	}

	/**
	 * Clones a java.util.List
	 * @param <E>
	 * The generic type of the list
	 * @param list
	 * The list to clone
	 * @return the cloned list, or an empty java.util.List if the list argument is <code>null</code>.
	 */
	public static <E> List<E> cloneNullSafety(final List<E> list) {
		return list == null ? Lists.newArrayList() : Lists.newArrayList(list);
	}

	/**
	 * Returns a new java.util.List if null
	 * @param <E>
	 * The generic type of the list
	 * @param list
	 * The list to check
	 * @return the list, or new ArrayList if the list argument is null.
	 */
	public static <E> List<E> nullSafety(final List<E> list) {
		return list == null ? Lists.newArrayList() : list;
	}

	/**
	 * Returns a new String composed of copies of the CharSequence elements joined together
	 * @param elements
	 * The elements to concatenate
	 * @return
	 * The concatenated String
	 */
	public static String join(String... elements) {
		return elements == null ? "" : String.join("", elements);
	}

}
