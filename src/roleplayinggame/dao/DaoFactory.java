package roleplayinggame.dao;

import roleplayinggame.constant.Constants;
import roleplayinggame.util.ResourceUtil;

/**
 * DaoDactory abstract class is the top level abstraction class used in Abstract Factory pattern. It holds reference to all the persistent stores as separate Factories which are extends the DaoFactory. The DEFAULT factory get from a properties file, so that with a single point of change we can
 * switch to another persisten storage without even touching the code.
 * 
 * @author Ali Gelenler
 *
 */
public abstract class DaoFactory {

	public static final DaoFactory FILE = new FileDaoFactory();

	// Use DEFAULT to be able to change the persistent store from one point of control
	public static final DaoFactory DEFAULT = getFactoryFromPropFile();

	/**
	 * This abstract method returns the GamePlayerDao interface to be able to use on the persistence operations. The implementation of this class will return the correspending persistence type's implementation.
	 * 
	 * @return GamePlayerDao
	 */
	public abstract GamePlayerDao getGamePlayerDao();

	/**
	 * This abstract method returns the GameListDao interface to be able to use on the persistence operations. The implementation of this class will return the correspending persistence type's implementation.
	 * 
	 * @return GameListDao
	 */
	public abstract GameDao getGameDao();

	/**
	 * Get the default factory from the persistence properties file
	 * 
	 * @return DaoFactory
	 */
	private static DaoFactory getFactoryFromPropFile() {
		String persistenceType = ResourceUtil.readFromPropFile(Constants.PERSISTENCE_TYPE_FILE, Constants.PERSISTENCE_TYPE_NAME);

		if (Constants.FILE_PERSISTENCE.equals(persistenceType))
			return FILE;
		else
			return FILE;// Default
	}

}
