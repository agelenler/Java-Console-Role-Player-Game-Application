package roleplayinggame.model;

import roleplayinggame.visitor.ReflectiveElement;
import roleplayinggame.visitor.ReflectiveVisitor;

/**
 * The ClassHolder class wraps a class in it.
 * 
 * @author Ali Gelenler
 *
 */
public class ClassHolder implements ReflectiveElement {

	private Class<?> klazz;

	public ClassHolder() {
		// TODO Auto-generated constructor stub
	}

	public ClassHolder(Class<?> klazz) {
		this.klazz = klazz;
	}

	public Class<?> getKlazz() {
		return klazz;
	}

	public void setKlazz(Class<?> klazz) {
		this.klazz = klazz;
	}

	@Override
	public Object accept(ReflectiveVisitor visitor, Object data, Class<?> klazz) {
		return visitor.visit(this, data, klazz);
	}

}
