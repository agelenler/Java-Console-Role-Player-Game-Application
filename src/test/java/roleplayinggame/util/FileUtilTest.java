package test.java.roleplayinggame.util;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import roleplayinggame.constant.Constants;
import roleplayinggame.model.FieldHolder;
import roleplayinggame.model.Game;

/**
 * File related utility operations
 * 
 * @author Ali Gelenler
 *
 */
public class FileUtilTest {

	private Field expectedField;
	private String expectedHeader;
	private StringBuilder expectedIsFound;
	private FieldHolder expectedFieldHolder;

	@Before
	public void initObjects() {
		try {
			expectedField = Game.class.getDeclaredField("player");
		} catch (NoSuchFieldException e) {
			Assert.fail(e.getMessage());
		} catch (SecurityException e) {
			Assert.fail(e.getMessage());
		}
		expectedHeader = "username";
		expectedIsFound = new StringBuilder("");
		expectedFieldHolder = new FieldHolder();
	}

	/**
	 * Deep Compare the fields of a class using recursion
	 * 
	 * @param field
	 * @param header
	 * @param isFound
	 */
	@Test
	public void compareInnerFields() {
		Field[] innerFields = expectedField.getType().getDeclaredFields();
		if (innerFields != null && innerFields.length > 0) {
			for (int l = 0; l < innerFields.length; l++) {
				if (innerFields[l].getType().isPrimitive() || innerFields[l].getType().equals(String.class)) {
					if (expectedHeader.equalsIgnoreCase(innerFields[l].getName())) {
						expectedIsFound.append(Constants.OK);
						expectedFieldHolder.setField(innerFields[l]);
					}
				}
				// else
				// compareInnerFields(innerFields[l], header, isFound, fieldHolder);
			}
		}
		Assert.assertEquals(expectedIsFound.toString(), Constants.OK);
	}

}
