package test.java.roleplayinggame.util;

import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import roleplayinggame.model.ClassHolder;
import roleplayinggame.visitor.ConcreteVisitor;
import roleplayinggame.visitor.ReflectiveVisitor;

/**
 * Reflection related utility operations
 * 
 * @author Ali Gelenler
 *
 */
public class ReflectionUtilTest {

	private Class<?> klazz;
	private ClassHolder type;
	private Object data;
	private ReflectiveVisitor visitor;
	private Object returnVal;
	private Class<?> type2;

	@Before
	public void initObjects() {
		klazz = Integer.class;
		type = new ClassHolder(int.class);
		data = "5";
		visitor = new ConcreteVisitor();
		type2 = Integer.class;
	}

	/**
	 * Get an object using a given class's constructor and a visitor object
	 * 
	 * @param klazz
	 * @param type
	 * @param data
	 * @param visitor
	 * @return Object
	 */
	@Test
	public void getDataFromType() {
		Object result = null;
		try {
			if (type.getKlazz().isPrimitive()) {
				result = type.accept(visitor, data, klazz);
			} else
				result = klazz.getDeclaredConstructor(String.class).newInstance(data);

			Assert.assertTrue(result instanceof Integer);

		} catch (InstantiationException e) {
			Assert.fail(e.getMessage());
		} catch (IllegalAccessException e) {
			Assert.fail(e.getMessage());
		} catch (IllegalArgumentException e) {
			Assert.fail(e.getMessage());
		} catch (InvocationTargetException e) {
			Assert.fail(e.getMessage());
		} catch (NoSuchMethodException e) {
			Assert.fail(e.getMessage());
		} catch (SecurityException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void getObjectFromType() {
		if (type2.equals(Integer.class) || type2.equals(int.class))
			returnVal = Integer.parseInt(data.toString());
		else if (type2.equals(Short.class) || type2.equals(short.class))
			returnVal = Short.parseShort(data.toString());
		else if (type2.equals(Long.class) || type2.equals(long.class))
			returnVal = Long.parseLong(data.toString());
		else if (type2.equals(Byte.class) || type2.equals(byte.class))
			returnVal = Byte.parseByte(data.toString());
		else if (type2.equals(Character.class) || type2.equals(char.class))
			returnVal = data.toString().charAt(0);
		else if (type2.equals(Double.class) || type2.equals(double.class))
			returnVal = Double.parseDouble(data.toString());
		else if (type2.equals(Float.class) || type2.equals(float.class))
			returnVal = Float.parseFloat(type2.toString());
		else
			returnVal = data.toString();

		Assert.assertTrue(returnVal instanceof Integer);

	}

}
