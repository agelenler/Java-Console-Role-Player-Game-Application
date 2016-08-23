package roleplayinggame.service;

import java.util.Scanner;

import roleplayinggame.exception.RolePlayingException;

/**
 * IRolePlayingGamePlayerService is the interface of Game Player Service
 * @author Ali Gelenler
 *
 */
public interface IRolePlayingGamePlayerService {

	/**
	 * This method called to sign in a player
	 * @param sn
	 * @return
	 * @throws RolePlayingException
	 */
	public boolean signIn(Scanner sn) throws RolePlayingException;

	/**
	 * This method called to sign up a player
	 * @param sn
	 * @return
	 * @throws RolePlayingException
	 */
	public boolean signUp(Scanner sn) throws RolePlayingException;

}
