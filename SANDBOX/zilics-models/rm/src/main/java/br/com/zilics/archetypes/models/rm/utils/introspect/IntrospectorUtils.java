package br.com.zilics.archetypes.models.rm.utils.introspect;

/**
 * Utility class that automatically deals the the official names of classes
 *  
 * @author Humberto Naves
 */
public final class IntrospectorUtils {
	private IntrospectorUtils() {}

	/**
	 * Converts a Java Camel Case class name (or field name) to the underline format (the format used by the openEHR).
	 * For example, it transforms "AbacDfGHtd" into "ABAC_DF_G_HTD" (if upperCase == true) 
	 * @param camel the string in camel case format
	 * @param upperCase true if all letters are going to be upper case
	 * @return the string in the underline format
	 */
	public static String camelCaseFormatToUnderlineFormat(String camel, boolean upperCase) {
		if (camel == null)
			return null;
		StringBuilder sb = new StringBuilder(camel.length() + 10);
		for (int i = 0; i < camel.length(); i++) {
			char ch = camel.charAt(i);
			if (Character.isUpperCase(ch) && i != 0)
				sb.append("_");
			if (upperCase)
				sb.append(Character.toUpperCase(ch));
			else
				sb.append(Character.toLowerCase(ch));
		}
		return sb.toString();
	}

	/**
	 * Do the reverse of {@link #camelCaseFormatToUnderlineFormat(String, boolean)}.
	 * For example, it transforms "AB_CDEF_G_H_TDE" into "AbCdefGHTde" (if firstUpper == true)
	 * @param underline the string in underline format
	 * @param firstUpper the first letter is upper case?
	 * @return the string in the camel case format
	 */
	public static String underlineFormatToCamelCaseFormat(String underline, boolean firstUpper) {
		if (underline == null)
			return null;
		StringBuilder sb = new StringBuilder(underline.length());
		boolean nextUpper = firstUpper;
		for (int i = 0; i < underline.length(); i++) {
			char ch = underline.charAt(i);
			if (ch == '_') {
				nextUpper = true;
				continue;
			}
			if (nextUpper)
				sb.append(Character.toUpperCase(ch));
			else
				sb.append(Character.toLowerCase(ch));
			nextUpper = false;
		}
		return sb.toString();
	}
}

	