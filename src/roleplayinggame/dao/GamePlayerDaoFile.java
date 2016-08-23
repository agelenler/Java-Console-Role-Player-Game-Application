package roleplayinggame.dao;

import roleplayinggame.model.Player;

/**
 * The implementation of GamePlayerDao.
 * 
 * @author Ali Gelenler
 *
 */
class GamePlayerDaoFile extends GenericDaoFile<Player> implements GamePlayerDao {

	public static final GamePlayerDao INSTANCE = new GamePlayerDaoFile();

	private GamePlayerDaoFile() {
		// TODO Auto-generated constructor stub
	}

}
