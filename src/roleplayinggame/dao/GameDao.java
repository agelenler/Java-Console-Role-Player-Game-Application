package roleplayinggame.dao;

import java.util.List;

import roleplayinggame.exception.RolePlayingException;
import roleplayinggame.model.Game;

/**
 * GameListDao is the interface to access to the game list persistent storage
 * 
 * @author Ali Gelenler
 *
 */
public interface GameDao extends GenericIDaoFile<Game> {

	/**
	 * Used to get the game list from the given file as a List of Game object. Can also use the generic readFileIntoList method in GenericIDaoFile instead of this method
	 * 
	 * @param filename
	 * @return List<Game>
	 * @throws RolePlayingException
	 * 
	 */
	public List<Game> getGameListFromFile(String filename) throws RolePlayingException;

}
