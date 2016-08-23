package roleplayinggame.service;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roleplayinggame.exception.RolePlayingException;
import roleplayinggame.exception.RolePlayingExceptionMessages;

/**
 * AbstractRolePlayingGamePlayerService used to create a template method pattern to access to the initial point and last point of
 * of control
 * @author Ali Gelenler
 *
 */
public abstract class AbstractRolePlayingGamePlayerService implements IRolePlayingGamePlayerService {

	public static final Logger logger = LoggerFactory.getLogger(AbstractRolePlayingGamePlayerService.class);

	@Override
	public boolean signIn(Scanner sn) throws RolePlayingException {
		try {
			return signInExecute(sn);
		} catch (RolePlayingException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0000);
		}
	}

	@Override
	public boolean signUp(Scanner sn) throws RolePlayingException {
		try {
			return signUpExecute(sn);
		} catch (RolePlayingException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0000);
		}
	}

	public abstract boolean signInExecute(Scanner sn) throws RolePlayingException, UnsupportedEncodingException;

	public abstract boolean signUpExecute(Scanner sn) throws RolePlayingException, UnsupportedEncodingException;

}
