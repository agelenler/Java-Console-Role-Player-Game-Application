package roleplayinggame.visitor;

/**
 * A Reflective visitor interface
 * 
 * @author Ali Gelenler
 *
 */
public interface ReflectiveVisitor {
	/**
	 * Return an object using Visitor design pattern with a reflection approach
	 * 
	 * @param o
	 * @param data
	 * @param klazz
	 * @return Object
	 */
	Object visit(Object o, Object data, Class<?> klazz);
}
