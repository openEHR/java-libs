package org.openehr.rm.util;

import java.util.List;

import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;

/**
 * Performs utility operations on {@link Item} 
 * @author Malik.Firose
 *
 */
public class ItemUtil {

	private static ItemUtil instance;

	private ItemUtil() {

	}

	public static ItemUtil getInstance() {
		if (instance == null) {
			instance = new ItemUtil();
		}
		return instance;
	}

	public boolean compareItems(final List<Item> thisItem,
			final List<Item> otherItem) {

		// Since the order of these two item lists cannot be assured following
		// approach is used
		boolean equal = true;
		for (final Item element : thisItem) {
			equal = otherItem.contains(element);
			if (!equal) {
				break;
			}
		}
		return equal;
	}

	public boolean compareElements(final List<Element> thisElement,
			final List<Element> otherElement) {
		// Since the order of these two element list cannot be assured following
		// approach is used
		boolean equal = true;
		for (final Element element : thisElement) {
			equal = otherElement.contains(element);
			if (!equal) {
				break;
			}
		}
		return equal;
	}
}
