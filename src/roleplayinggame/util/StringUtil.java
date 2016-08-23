package roleplayinggame.util;

import roleplayinggame.constant.Constants;

/**
 * String related utility operations
 * 
 * @author Ali Gelenler
 *
 */
public abstract class StringUtil {

	/**
	 * Get a string with the first char upper
	 * 
	 * @param value
	 * @return String
	 */
	public static String getFirstCharUpper(String value) {
		return String.valueOf(value.charAt(0)).toUpperCase(Constants.DEFAULT_LOCALE) + value.substring(1, value.length()).toLowerCase(Constants.DEFAULT_LOCALE);
	}

	/**
	 * Get a string with the first char upper without touching rest
	 * 
	 * @param value
	 * @return String
	 */
	public static String getOnlyFirstCharUpper(String value) {
		return String.valueOf(value.charAt(0)).toUpperCase(Constants.DEFAULT_LOCALE) + value.substring(1, value.length());
	}

	/**
	 * Get a string with the first char lower
	 * 
	 * @param value
	 * @return String
	 */
	public static String getFirstCharLower(String value) {
		return String.valueOf(value.charAt(0)).toLowerCase(Constants.DEFAULT_LOCALE) + value.substring(1, value.length());
	}

	/**
	 * Get a string making it lower camel case
	 * 
	 * @param value
	 * @return String
	 */
	public static String makeTheStringLowerCamelCase(String value, String regex) {
		String[] parts = value.split(regex);
		StringBuilder lowerCamelCaseValue = new StringBuilder();
		for (int i = 0; i < parts.length; i++) {
			lowerCamelCaseValue.append(i == 0 ? parts[i].toLowerCase(Constants.DEFAULT_LOCALE) : getFirstCharUpper(parts[i]));
		}
		return lowerCamelCaseValue.toString();
	}

	/**
	 * Get a string making it lower camel case lefting the very first letter upper
	 * 
	 * @param value
	 * @return String
	 */
	public static String makeTheStringLowerCamelCaseWithUpperBeginning(String value, String regex) {
		String[] parts = value.split(regex);
		StringBuilder lowerCamelCaseValue = new StringBuilder();
		for (int i = 0; i < parts.length; i++) {
			lowerCamelCaseValue.append(getFirstCharUpper(parts[i]));
		}
		return lowerCamelCaseValue.toString();
	}

	/**
	 * Check if a String is empty
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || input.length() == 0)
			return true;
		else
			return false;
	}

}
