package test.java.roleplayinggame.service;

import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import roleplayinggame.constant.Constants;
import roleplayinggame.dao.DaoFactory;
import roleplayinggame.exception.RolePlayingException;
import roleplayinggame.helper.GenericHelper;
import roleplayinggame.helper.RolePlayingGameServiceHelper;
import roleplayinggame.model.Experience;
import roleplayinggame.model.Game;
import roleplayinggame.model.GameCharacter;
import roleplayinggame.model.Player;
import roleplayinggame.model.Position;
import roleplayinggame.staticdata.GameData;

public class RolePlayingGameServiceTest {

	private String expectedUserChoose;
	private String currentPlayerName;
	private Game expectedGame = null;

	@Before
	public void initObjects() {
		expectedUserChoose = Constants.MENU_CHOOSE_1;
		currentPlayerName = "ali";
		expectedGame = new Game();
		expectedGame.setGameId(1664596199);
		Player player = new Player("agelenler");
		expectedGame.setPlayer(player);
		GameCharacter gameCharacter = new GameCharacter();
		gameCharacter.setCharacterId(1);
		gameCharacter.setExperience(new Experience(8));
		gameCharacter.setPosition(new Position(5, 6));
		expectedGame.setGameCharacter(gameCharacter);
		expectedGame.setBordersX(10);
		expectedGame.setBordersY(10);
	}

	@Test
	public void startGameExecute() throws RolePlayingException {
		List<Game> games = DaoFactory.DEFAULT.getGameDao().readFileIntoList(Game.class, Constants.FILE_SAVED_GAMES);
		String userChoose = null;
		if (!GenericHelper.isEmpty(games)) {
			// println(Constants.START_GAME);
			GameData.currentPlayer = new Player(currentPlayerName);
			userChoose = expectedUserChoose;
			if (Constants.MENU_CHOOSE_1.equals(userChoose)) {
				Assert.assertNotNull(games);
			} else if (Constants.MENU_CHOOSE_2.equals(userChoose)) {
				Game loadGame = expectedGame;
				GameCharacter currentCharacter = GameData.gameCharacters.get(loadGame.getGameCharacter().getCharacterId() - 1);
				currentCharacter.setPosition(loadGame.getGameCharacter().getPosition());
				currentCharacter.setExperience(loadGame.getGameCharacter().getExperience());
				List<Game> tmpGameList = RolePlayingGameServiceHelper.getInstance().moveAndPlay(loadGame.getBordersX(), loadGame.getBordersY(), currentCharacter, loadGame, games, new Scanner(System.in));
				if (tmpGameList != null)
					games = tmpGameList;
				Assert.assertNotNull(loadGame);
			}
		} else {
			Assert.assertNotNull(games);
		}
	}

}
