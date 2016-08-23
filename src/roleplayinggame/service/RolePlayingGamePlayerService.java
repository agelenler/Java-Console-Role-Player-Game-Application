package roleplayinggame.service;

import static roleplayinggame.util.PrintUtil.println;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import roleplayinggame.constant.Constants;
import roleplayinggame.dao.DaoFactory;
import roleplayinggame.exception.RolePlayingException;
import roleplayinggame.exception.RolePlayingExceptionMessages;
import roleplayinggame.helper.GenericHelper;
import roleplayinggame.helper.RolePlayingGamePlayerServiceHelper;
import roleplayinggame.model.Player;
import roleplayinggame.staticdata.GameData;
import roleplayinggame.util.StringUtil;

/**
 * RolePlayingGamePlayerService holds the game player related methods
 * @author Ali Gelenler
 *
 */
public class RolePlayingGamePlayerService extends AbstractRolePlayingGamePlayerService implements IRolePlayingGamePlayerService {

	private RolePlayingGamePlayerService() {

	}

	private static class LazyRolePlayingGamePlayerServiceHolder {
		// Thread-safe without synchronization using static and final
		private static final IRolePlayingGamePlayerService INSTANCE = new RolePlayingGamePlayerService();
	}

	public static IRolePlayingGamePlayerService getInstance() {
		// Lazy creation of Singleton
		return LazyRolePlayingGamePlayerServiceHolder.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * @see roleplayinggame.service.AbstractRolePlayingGamePlayerService#signInExecute(java.util.Scanner)
	 */
	@Override
	public boolean signInExecute(Scanner sn) throws RolePlayingException, UnsupportedEncodingException {
		while (GameData.currentPlayer == null) {
			println(Constants.ENTER_USERNAME);
			String username = sn.next();
			if (GenericHelper.goPrevMenu(username))
				return true;
			println(Constants.ENTER_PASSWORD);
			String tmpPwd = sn.next();
			if (GenericHelper.goPrevMenu(tmpPwd))
				return true;
			String password = RolePlayingGamePlayerServiceHelper.getInstance().getEncodedPwd(tmpPwd);
			List<Player> players = DaoFactory.DEFAULT.getGamePlayerDao().readFileIntoList(Player.class, Constants.FILE_GAME_PLAYERS);
			Optional<Player> tmpCurrrentPlayer = players.stream().filter(p -> p != null && p.getUsername() != null && p.getPassword() != null && p.getUsername().equals(username) && p.getPassword().equals(password)).findAny();
			GameData.currentPlayer = tmpCurrrentPlayer.orElse(null);
			if (GameData.currentPlayer == null)
				println(Constants.WRONG_INFO_TRY_AGAIN);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see roleplayinggame.service.AbstractRolePlayingGamePlayerService#signUpExecute(java.util.Scanner)
	 */
	@Override
	public boolean signUpExecute(Scanner sn) throws RolePlayingException, UnsupportedEncodingException {
		boolean result = false;
		do {
			println(Constants.ENTER_NAME);
			String name = sn.next();
			if (GenericHelper.goPrevMenu(name))
				return true;
			println(Constants.ENTER_SURNAME);
			String surname = sn.next();
			if (GenericHelper.goPrevMenu(surname))
				return true;
			println(Constants.ENTER_USERNAME);
			String username = sn.next();
			if (GenericHelper.goPrevMenu(username))
				return true;
			println(Constants.ENTER_PASSWORD);
			String password = sn.next();
			if (GenericHelper.goPrevMenu(password))
				return true;
			password = RolePlayingGamePlayerServiceHelper.getInstance().getEncodedPwd(password);
			if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password) || StringUtil.isEmpty(name) || StringUtil.isEmpty(surname)) {
				throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0004);
			}
			List<Player> players = DaoFactory.DEFAULT.getGamePlayerDao().readFileIntoList(Player.class, Constants.FILE_GAME_PLAYERS);
			Optional<Player> tmpCurrrentPlayer = players.stream().filter(p -> p != null && p.getUsername() != null && p.getUsername().equals(username)).findAny();
			Player tmpPlayer = tmpCurrrentPlayer.orElse(null);
			if (tmpPlayer != null)
				throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0005);
			Player player = new Player(name, surname, username, password);
			result = DaoFactory.DEFAULT.getGamePlayerDao().writeDataIntoFile(player, Constants.FILE_GAME_PLAYERS, true);
			if (!result)
				println(RolePlayingExceptionMessages.roleplaying_0000.getErrorMessage());
			else
				GameData.currentPlayer = player;
		} while (!result);
		return false;
	}
}
