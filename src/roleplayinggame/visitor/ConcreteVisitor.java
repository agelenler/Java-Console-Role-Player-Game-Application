package roleplayinggame.visitor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roleplayinggame.model.ClassHolder;

/**
 * Implementation of ReflectiveVisitor
 * 
 * @author Ali Gelenler
 *
 */
public class ConcreteVisitor implements ReflectiveVisitor {

	public static final Logger logger = LoggerFactory.getLogger(ConcreteVisitor.class);

	static Map<String, Object> wrapperMap = new HashMap<String, Object>();
	static {
		wrapperMap.put("int", new Integer(1));
		wrapperMap.put("byte", Byte.class);
		wrapperMap.put("short", Short.class);
		wrapperMap.put("long", Long.class);
		wrapperMap.put("char", Character.class);
		wrapperMap.put("boolean", Boolean.class);
		wrapperMap.put("float", Float.class);
		wrapperMap.put("double", Double.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see roleplayinggame.visitor.ReflectiveVisitor#visit(java.lang.Object, java.lang.Object, java.lang.Class)
	 */
	@Override
	public Object visit(Object o, Object data, Class<?> klazz) {
		try {
			Method visitMethod = this.getClass().getMethod("visit", new Class[] { ((ClassHolder) o).getKlazz(), data.getClass(), klazz.getClass() });
			if (visitMethod == null) {
				return defaultVisit(o);
			} else {
				return visitMethod.invoke(this, new Object[] { wrapperMap.get(((ClassHolder) o).getKlazz().getName()), data, klazz });
			}
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), e);
			this.defaultVisit(o);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(), e);
			this.defaultVisit(o);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
			this.defaultVisit(o);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(), e);
			this.defaultVisit(o);
		}
		return o;
	}

	/**
	 * Return an object creating it by reflection
	 * 
	 * @param type
	 * @param data
	 * @param klazz
	 * @return Object
	 */
	public Object visit(int type, String data, Class<?> klazz) {
		try {
			return klazz.getConstructor(int.class).newInstance(new Integer(data.toString()).intValue());
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
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
		return klazz;
	}

	/**
	 * Return an object creating it by reflection
	 * 
	 * @param type
	 * @param data
	 * @param klazz
	 * @return Object
	 */
	public Object visit(double type, String data, Class<?> klazz) {
		try {
			return klazz.getConstructor(double.class).newInstance(new Double(data.toString()).doubleValue());
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
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
		return klazz;
	}

	/**
	 * Return an object creating it by reflection
	 * 
	 * @param type
	 * @param data
	 * @param klazz
	 * @return Object
	 */
	public Object visit(float type, String data, Class<?> klazz) {
		try {
			return klazz.getConstructor(float.class).newInstance(new Float(data.toString()).floatValue());
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
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
		return klazz;
	}

	/**
	 * Return an object creating it by reflection
	 * 
	 * @param type
	 * @param data
	 * @param klazz
	 * @return Object
	 */
	public Object visit(char type, String data, Class<?> klazz) {
		try {
			return klazz.getConstructor(char.class).newInstance(data.charAt(0));
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
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
		return klazz;
	}

	/**
	 * Return an object creating it by reflection
	 * 
	 * @param type
	 * @param data
	 * @param klazz
	 * @return Object
	 */
	public Object visit(boolean type, String data, Class<?> klazz) {
		try {
			return klazz.getConstructor(boolean.class).newInstance(new Boolean(data.toString()).booleanValue());
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
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
		return klazz;
	}

	/**
	 * Return an object creating it by reflection
	 * 
	 * @param type
	 * @param data
	 * @param klazz
	 * @return Object
	 */
	public Object visit(short type, String data, Class<?> klazz) {
		try {
			return klazz.getConstructor(short.class).newInstance(new Short(data.toString()).shortValue());
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
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
		return klazz;
	}

	/**
	 * Return an object creating it by reflection
	 * 
	 * @param type
	 * @param data
	 * @param klazz
	 * @return Object
	 */
	public Object visit(byte type, String data, Class<?> klazz) {
		try {
			return klazz.getConstructor(byte.class).newInstance(new Byte(data.toString()).byteValue());
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
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
		return klazz;
	}

	/**
	 * Return an object creating it by reflection
	 * 
	 * @param type
	 * @param data
	 * @param klazz
	 * @return Object
	 */
	public Object visit(long type, String data, Class<?> klazz) {
		try {
			return klazz.getConstructor(long.class).newInstance(new Long(data.toString()).longValue());
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
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
		return klazz;
	}

	/**
	 * The default visit operation
	 * 
	 * @param type
	 * @param data
	 * @param klazz
	 * @return Object
	 */
	public Object defaultVisit(Object o) {
		// TODO
		// System.out.println(o.toString());
		return null;
	}
}