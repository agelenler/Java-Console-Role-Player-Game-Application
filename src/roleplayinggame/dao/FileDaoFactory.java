package roleplayinggame.dao;

/**
 * FileDaoFactory is the File persistence implementation of DaoFactory class.
 * 
 * @author Ali Gelenler
 *
 */
public class FileDaoFactory extends DaoFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see roleplayinggame.dao.DaoFactory#getGamePlayerDao()
	 */
	@Override
	public GamePlayerDao getGamePlayerDao() {
		return GamePlayerDaoFile.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see roleplayinggame.dao.DaoFactory#getGameListDao()
	 */
	@Override
	public GameDao getGameDao() {
		return GameDaoFile.INSTANCE;
	}

}
