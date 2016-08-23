package roleplayinggame.util;

import java.lang.reflect.Field;

import roleplayinggame.constant.Constants;
import roleplayinggame.model.FieldHolder;

/**
 * File related utility operations
 * 
 * @author Ali Gelenler
 *
 */
public class FileUtil {

	private FileUtil() {

	}

	/**
	 * Deep Compare the fields of a class using recursion
	 * 
	 * @param field
	 * @param header
	 * @param isFound
	 */
	public static void compareInnerFields(Field field, String header, StringBuilder isFound, FieldHolder fieldHolder) {
		Field[] innerFields = field.getType().getDeclaredFields();
		if (innerFields != null && innerFields.length > 0) {
			for (int l = 0; l < innerFields.length; l++) {
				if (innerFields[l].getType().isPrimitive() || innerFields[l].getType().equals(String.class)) {
					if (header.equalsIgnoreCase(innerFields[l].getName())) {
						isFound.append(Constants.OK);
						fieldHolder.setField(innerFields[l]);
					}
				} else
					compareInnerFields(innerFields[l], header, isFound, fieldHolder);
			}
		}
	}

}
