package roleplayinggame.util;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roleplayinggame.model.ClassHolder;
import roleplayinggame.visitor.ReflectiveVisitor;

/**
 * Reflection related utility operations
 * 
 * @author Ali Gelenler
 *
 */
public abstract class ReflectionUtil {

	public static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

	/**
	 * Get an object using a given class's constructor and a visitor object
	 * 
	 * @param klazz
	 * @param type
	 * @param data
	 * @param visitor
	 * @return Object
	 */
	public static Object getDataFromType(Class<?> klazz, ClassHolder type, Object data, ReflectiveVisitor visitor) {
		Object result = null;
		try {
			if (type.getKlazz().isPrimitive()) {
				result = type.accept(visitor, data, klazz);
			} else
				return klazz.getDeclaredConstructor(String.class).newInstance(data);
		} catch (InstantiationException e) {
			logger.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), e);
		} catch (SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	public static Object getObjectFromType(Object data, Class<?> type) {
		if (type.equals(Integer.class) || type.equals(int.class))
			return Integer.parseInt(data.toString());
		else if (type.equals(Short.class) || type.equals(short.class))
			return Short.parseShort(data.toString());
		else if (type.equals(Long.class) || type.equals(long.class))
			return Long.parseLong(data.toString());
		else if (type.equals(Byte.class) || type.equals(byte.class))
			return Byte.parseByte(data.toString());
		else if (type.equals(Character.class) || type.equals(char.class))
			return data.toString().charAt(0);
		else if (type.equals(Double.class) || type.equals(double.class))
			return Double.parseDouble(data.toString());
		else if (type.equals(Float.class) || type.equals(float.class))
			return Float.parseFloat(data.toString());
		else
			return data.toString();
	}

}
