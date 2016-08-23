package roleplayinggame.service;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roleplayinggame.exception.RolePlayingException;
import roleplayinggame.exception.RolePlayingExceptionMessages;

/**
 * AbstractRolePlayingGameService used to create a template method pattern to access to the initial point and last point of
 * of control
 * @author Ali Gelenler
 *
 */
public abstract class AbstractRolePlayingGameService implements IRolePlayingGameService {

	public static final Logger logger = LoggerFactory.getLogger(AbstractRolePlayingGameService.class);

	@Override
	public void startGame(Scanner sn) throws RolePlayingException {
		try {
			startGameExecute(sn);
		} catch (RolePlayingException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0000);
		}
	}

	public abstract void startGameExecute(Scanner sn) throws RolePlayingException;

}
