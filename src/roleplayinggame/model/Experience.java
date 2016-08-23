package roleplayinggame.model;

/**
 * The Experience class is the model of the experince point of a user wrapping an int variable.
 * 
 * @author Ali Gelenler
 *
 */
public class Experience {
	private int point;

	public Experience(int point) {
		this.point = point;
	}

	public Experience() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + point;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Experience other = (Experience) obj;
		if (point != other.point)
			return false;
		return true;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
}
