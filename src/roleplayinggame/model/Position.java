package roleplayinggame.model;

/**
 * The Position class is the model of x and y coordinates of a particular Character
 * 
 * @author Ali Gelenler
 *
 */
public class Position {

	private int lastPositionX;
	private int lastPositionY;

	@Override
	public String toString() {
		return lastPositionX + "	" + lastPositionY;
	}

	public Position() {
		// TODO Auto-generated constructor stub
	}

	public Position(int lastPositionX, int lastPositionY) {
		this.lastPositionX = lastPositionX;
		this.lastPositionY = lastPositionY;
	}

	public int getLastPositionX() {
		return lastPositionX;
	}

	public void setLastPositionX(int lastPositionX) {
		this.lastPositionX = lastPositionX;
	}

	public int getLastPositionY() {
		return lastPositionY;
	}

	public void setLastPositionY(int lastPositionY) {
		this.lastPositionY = lastPositionY;
	}
}
