package test.java.roleplayinggame.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import roleplayinggame.constant.Constants;

/**
 * String related utility operations
 * 
 * @author Ali Gelenler
 *
 */
public class StringUtilTest {

	private String valueUpper;
	private String expectedValueGetFirstCharUpper;
	private String expectedValueGetOnlyFirstCharUpper;

	private String valueLower;
	private String expectedValueGetFirstCharLower;

	private String lowerCamelCalseValue;
	private String regex;
	private String expectedLowerCamelCaseValue;
	private String expectedLowerCamelCaseValueUpperBeginning;
	private String[] expectedParts = new String[] { "Last", "Position", "X" };

	private boolean expectedIsEmpty;
	private String expectedEmptyStr;

	@Before
	public void initObjects() {
		valueUpper = "value";
		expectedValueGetFirstCharUpper = "Value";
		expectedValueGetOnlyFirstCharUpper = "Value";

		valueLower = "VAlue";
		expectedValueGetFirstCharLower = "vAlue";

		regex = "_";
		lowerCamelCalseValue = "LAST_POSITION_X";
		expectedLowerCamelCaseValue = "lastPositionX";
		expectedLowerCamelCaseValueUpperBeginning = "LastPositionX";

		expectedIsEmpty = true;
		expectedEmptyStr = null;

	}

	/**
	 * Get a string with the first char upper
	 * 
	 * @param value
	 * @return String
	 */
	@Test
	public void getFirstCharUpper() {
		Assert.assertEquals(expectedValueGetFirstCharUpper, String.valueOf(valueUpper.charAt(0)).toUpperCase(Constants.DEFAULT_LOCALE) + valueUpper.substring(1, valueUpper.length()).toLowerCase(Constants.DEFAULT_LOCALE));
	}

	/**
	 * Get a string with the first char upper without touching rest
	 * 
	 * @param value
	 * @return String
	 */
	@Test
	public void getOnlyFirstCharUpper() {
		Assert.assertEquals(expectedValueGetOnlyFirstCharUpper, String.valueOf(valueUpper.charAt(0)).toUpperCase(Constants.DEFAULT_LOCALE) + valueUpper.substring(1, valueUpper.length()).toLowerCase(Constants.DEFAULT_LOCALE));
	}

	/**
	 * Get a string with the first char lower
	 * 
	 * @param value
	 * @return String
	 */
	@Test
	public void getFirstCharLower() {
		Assert.assertEquals(expectedValueGetFirstCharLower, String.valueOf(valueLower.charAt(0)).toLowerCase(Constants.DEFAULT_LOCALE) + valueLower.substring(1, valueLower.length()));
	}

	/**
	 * Get a string making it lower camel case
	 * 
	 * @param value
	 * @return String
	 */
	@Test
	public void makeTheStringLowerCamelCase() {
		String[] parts = lowerCamelCalseValue.split(regex);
		StringBuilder lowerCamelCaseValue = new StringBuilder();
		for (int i = 0; i < parts.length; i++) {
			lowerCamelCaseValue.append(i == 0 ? parts[i].toLowerCase(Constants.DEFAULT_LOCALE) : expectedParts[i]);
		}

		Assert.assertTrue(lowerCamelCaseValue.toString().equals(expectedLowerCamelCaseValue));

	}

	/**
	 * Get a string making it lower camel case lefting the very first letter upper
	 * 
	 * @param value
	 * @return String
	 */
	@Test
	public void makeTheStringLowerCamelCaseWithUpperBeginning() {
		String[] parts = lowerCamelCalseValue.split(regex);
		StringBuilder lowerCamelCaseValue = new StringBuilder();
		for (int i = 0; i < parts.length; i++) {
			lowerCamelCaseValue.append(expectedParts[i]);
		}
		Assert.assertTrue(lowerCamelCaseValue.toString().equals(expectedLowerCamelCaseValueUpperBeginning));
	}

	/**
	 * Check if a String is empty
	 * 
	 * @param value
	 * @return boolean
	 */
	@Test
	public void isEmpty() {
		if (expectedEmptyStr == null || expectedEmptyStr.length() == 0)
			Assert.assertTrue(expectedIsEmpty);

	}

}
