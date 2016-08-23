package test.java.roleplayinggame.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import roleplayinggame.constant.Constants;
import roleplayinggame.dao.DaoFactory;
import roleplayinggame.exception.RolePlayingException;
import roleplayinggame.exception.RolePlayingExceptionMessages;
import roleplayinggame.helper.RolePlayingGamePlayerServiceHelper;
import roleplayinggame.model.Player;
import roleplayinggame.staticdata.GameData;
import roleplayinggame.util.StringUtil;

public class RolePlayingGamePlayerServiceTest {

	private String expectedName;
	private String expectedSurname;
	private String expectedUsername;
	private String expectedPassword;
	private String expectedNewUsername;

	@Before
	public void initObjects() {
		expectedName = "c";
		expectedSurname = "c";
		expectedUsername = "ali";
		expectedNewUsername = "ali" + new Random().nextInt();
		expectedPassword = "1234";
	}

	@Test
	public void signInExecute() throws RolePlayingException, UnsupportedEncodingException {
		while (GameData.currentPlayer == null) {
			String username = expectedUsername;
			String tmpPwd = expectedPassword;
			String password = RolePlayingGamePlayerServiceHelper.getInstance().getEncodedPwd(tmpPwd);
			List<Player> players = DaoFactory.DEFAULT.getGamePlayerDao().readFileIntoList(Player.class, Constants.FILE_GAME_PLAYERS);
			Optional<Player> tmpCurrrentPlayer = players.stream().filter(p -> p != null && p.getUsername() != null && p.getPassword() != null && p.getUsername().equals(username) && p.getPassword().equals(password)).findAny();
			GameData.currentPlayer = tmpCurrrentPlayer.orElse(null);

			Assert.assertTrue(GameData.currentPlayer != null);

		}
	}

	@Test
	public void signUpExecute() throws RolePlayingException, UnsupportedEncodingException {
		boolean result = false;
		do {
			String name = expectedName;

			String surname = expectedSurname;

			String username = expectedNewUsername;

			String password = expectedPassword;

			password = RolePlayingGamePlayerServiceHelper.getInstance().getEncodedPwd(password);
			if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password) || StringUtil.isEmpty(name) || StringUtil.isEmpty(surname)) {
				Assert.fail(RolePlayingExceptionMessages.roleplaying_0004.getErrorMessage());
			}
			List<Player> players = DaoFactory.DEFAULT.getGamePlayerDao().readFileIntoList(Player.class, Constants.FILE_GAME_PLAYERS);
			Optional<Player> tmpCurrrentPlayer = players.stream().filter(p -> p != null && p.getUsername() != null && p.getUsername().equals(username)).findAny();
			Player tmpPlayer = tmpCurrrentPlayer.orElse(null);
			Player player = null;
			if (tmpPlayer != null)
				Assert.fail(RolePlayingExceptionMessages.roleplaying_0005.getErrorMessage());
			else {
				player = new Player(name, surname, username, password);
				result = DaoFactory.DEFAULT.getGamePlayerDao().writeDataIntoFile(player, Constants.FILE_GAME_PLAYERS, true);

			}
			Assert.assertTrue(result);

			GameData.currentPlayer = player;
		} while (!result);
	}
}
