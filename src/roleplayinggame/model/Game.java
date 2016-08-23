package roleplayinggame.model;

/**
 * The Game class is the model of the game info with composite Player and GameCharacter classes
 * 
 * @see Player
 * @see GameCharacter
 * @author Ali Gelenler
 *
 */
public class Game {
	private int gameId;
	private Player player;
	private GameCharacter gameCharacter;
	private int bordersX;
	private int bordersY;

	public Game(int gameId, Player player, GameCharacter gameCharacter, int bordersX, int bordersY) {
		this.gameId = gameId;
		this.player = player;
		this.gameCharacter = gameCharacter;
		this.bordersX = bordersX;
		this.bordersY = bordersY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + gameId;
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
		Game other = (Game) obj;
		if (gameId != other.gameId)
			return false;
		return true;
	}

	public Game() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return gameId + "	" + player.getUsername() + "	" + gameCharacter.getCharacterId() + "	" + gameCharacter.getPosition() + "	" + gameCharacter.getExperience().getPoint() + "	" + bordersX + "	" + bordersY;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public GameCharacter getGameCharacter() {
		return gameCharacter;
	}

	public void setGameCharacter(GameCharacter gameCharacter) {
		this.gameCharacter = gameCharacter;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getBordersX() {
		return bordersX;
	}

	public void setBordersX(int bordersX) {
		this.bordersX = bordersX;
	}

	public int getBordersY() {
		return bordersY;
	}

	public void setBordersY(int bordersY) {
		this.bordersY = bordersY;
	}
}
