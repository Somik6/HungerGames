package com.tips48.hungergames.utils;

import java.util.Collection;

/**
 * Handles general utility methods not specific enough to go in any other class
 * 
 * @author tips48
 * 
 */
public class Utils {
	private Utils() {

	}

	/**
	 * Parses a collection into a readable form
	 * 
	 * @param collection
	 *            Collection to parse
	 * @return Readable form
	 */
	public static String makeReadable(Collection<?> collection) {
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		for (Object o : collection) {
			if (first) {
				builder.append(o);
				first = false;
			} else {
				builder.append(",").append(o);
			}
		}
		return builder.toString();
	}

}
