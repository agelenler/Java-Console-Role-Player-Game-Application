package roleplayinggame.service;

import java.util.Scanner;

import roleplayinggame.exception.RolePlayingException;

/**
 * AbstractRolePlayingGameService is the interface of Game Service
 * @author Ali Gelenler
 *
 */
public interface IRolePlayingGameService {

	/**
	 * This method start or load a new game
	 * @param sn
	 * @throws RolePlayingException
	 */
	public void startGame(Scanner sn) throws RolePlayingException;

}
