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
	 * @param list
	 * @return the cloned list, or an empty java.util.List if the list argument is <code>null<code>.
	 */
	public static <E> List<E> cloneNullSafety(final List<E> list) {
		return list == null ? Lists.newArrayList() : Lists.newArrayList(list);
	}

	/**
	 * Returns a new java.util.List if <code>null<code>
	 * @param list
	 * @return the list, or new ArrayList if the list argument is <code>null<code>.
	 */
	public static <E> List<E> nullSafety(final List<E> list) {
		return list == null ? Lists.newArrayList() : list;
	}

}
