package roleplayinggame.helper;

import java.util.List;
import java.util.Scanner;

import roleplayinggame.exception.RolePlayingException;
import roleplayinggame.model.Game;
import roleplayinggame.model.GameCharacter;

/**
 * IRolePlayingGameServiceHelper is the interface of helper class of the RolePlayingGameService
 * 
 * @author Ali Gelenler
 *
 */
public interface IRolePlayingGameServiceHelper {

	/**
	 * Creates a new game and return the game list including the newly created game
	 * 
	 * @param sn
	 * @return List<Game>
	 * @throws RolePlayingException
	 */
	public List<Game> newGame(Scanner sn) throws RolePlayingException;

	/**
	 * Make the move on the map until user wants to quit. When quitting if the user wants to save the game, save it to persistent store and return the updated game list
	 * 
	 * @param borders
	 * @param currentCharacter
	 * @param game
	 * @param games
	 * @param sn
	 * @return List<Game>
	 * @throws RolePlayingException
	 */
	public List<Game> moveAndPlay(int borderX, int borderY, GameCharacter currentCharacter, Game game, List<Game> games, Scanner sn) throws RolePlayingException;

	/**
	 * Load a saved game and return it
	 * 
	 * @param games
	 * @param sn
	 * @return Game
	 * @throws RolePlayingException
	 */
	public Game loadGame(List<Game> games, Scanner sn) throws RolePlayingException;

}
