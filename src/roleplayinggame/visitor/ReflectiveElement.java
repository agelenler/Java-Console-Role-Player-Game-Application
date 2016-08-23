package roleplayinggame.visitor;

/**
 * This class used in visitor pattern to accept visitor interface and data
 * 
 * @author Ali Gelenler
 *
 */
public interface ReflectiveElement {
	Object accept(ReflectiveVisitor visitor, Object data, Class<?> klazz);
}