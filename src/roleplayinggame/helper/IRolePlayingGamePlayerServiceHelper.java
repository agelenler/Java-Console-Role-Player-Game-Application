package roleplayinggame.helper;

import roleplayinggame.exception.RolePlayingException;

/**
 * IRolePlayingGamePlayerServiceHelper is the interface of helper class of the RolePlayingGamePlayerService.
 * 
 * @author Ali Gelenler
 *
 */
public interface IRolePlayingGamePlayerServiceHelper {

	/**
	 * Return base 64 encoded password
	 * 
	 * @param input
	 * @return String
	 * @throws RolePlayingException
	 */
	public String getEncodedPwd(String input) throws RolePlayingException;

}
