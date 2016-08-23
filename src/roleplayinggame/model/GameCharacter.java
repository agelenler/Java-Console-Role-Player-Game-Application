package roleplayinggame.model;

/**
 * The GameCharacter class is the model of a character of the story with composite Experince and Position classes
 * 
 * @see Experience
 * @see Position
 * @author Ali Gelenler
 *
 */
public class GameCharacter {
	private int characterId;
	private String name;
	private Experience experience;
	private Position position;

	public GameCharacter(int characterId) {
		this.characterId = characterId;
	}

	public GameCharacter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object obj) {
		return this.characterId == ((GameCharacter) obj).characterId;
	}

	@Override
	public int hashCode() {
		if (this.name != null)
			return 31 * this.characterId + this.name.hashCode();
		else
			return 31 * this.characterId;
	}

	public int getCharacterId() {
		return characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
