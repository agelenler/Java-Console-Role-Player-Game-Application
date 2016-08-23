package test.java.roleplayinggame.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;

import roleplayinggame.constant.Constants;
import roleplayinggame.exception.RolePlayingException;
import roleplayinggame.model.Experience;
import roleplayinggame.model.Game;
import roleplayinggame.model.GameCharacter;
import roleplayinggame.model.Player;
import roleplayinggame.model.Position;
import roleplayinggame.util.StringUtil;

/**
 * Instead of this class, the generic method in GenericDaoFile is using, so skip this test
 * 
 * @author Ali Gelenler
 *
 */
public class GameListDaoFileTest {

	private List<Game> expectedGames = null;
	private Game expectedGame = null;
	private String inputFileName = null;

	public GameListDaoFileTest() {
		// TODO Auto-generated constructor stub
	}

	@Before
	public void initObjects() {
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
		expectedGames = new ArrayList<>();
		expectedGames.add(expectedGame);
		inputFileName = Constants.FILE_SAVED_GAMES;
	}

	// @Test
	public void getGameListFromFile() throws RolePlayingException, IOException {
		try {
			List<Game> gameList = new ArrayList<>();
			try (Stream<String> stream = Files.lines(Paths.get(inputFileName))) {
				List<String> tmpList = stream.skip(1).collect(Collectors.toList());
				for (String tmpStr : tmpList) {
					if (!StringUtil.isEmpty(tmpStr)) {
						String[] tmpArr = tmpStr.split(Constants.FILE_DELIMITER);
						int gameId = Integer.parseInt(tmpArr[0]);
						Player player = new Player(tmpArr[1]);

						Assert.assertTrue(player.getUsername().equals(expectedGame.getPlayer().getUsername()));

						int characterId = Integer.parseInt(tmpArr[2]);
						int positionX = Integer.parseInt(tmpArr[3]);
						int positionY = Integer.parseInt(tmpArr[4]);
						Position position = new Position(positionX, positionY);

						Assert.assertTrue(position.getLastPositionX() == expectedGame.getGameCharacter().getPosition().getLastPositionX());
						Assert.assertTrue(position.getLastPositionY() == expectedGame.getGameCharacter().getPosition().getLastPositionY());

						Experience experience = new Experience(Integer.parseInt(tmpArr[5]));

						Assert.assertTrue(experience.getPoint() == expectedGame.getGameCharacter().getExperience().getPoint());

						int bordersX = Integer.parseInt(tmpArr[6]);
						int bordersY = Integer.parseInt(tmpArr[7]);

						GameCharacter gameCharacter = new GameCharacter();
						gameCharacter.setCharacterId(characterId);
						gameCharacter.setExperience(experience);
						gameCharacter.setPosition(position);

						Game game = new Game();
						game.setGameId(gameId);
						game.setPlayer(player);
						game.setGameCharacter(gameCharacter);
						game.setBordersX(bordersX);
						game.setBordersY(bordersY);
						gameList.add(game);
					}
				}
			}

			Assert.assertTrue(gameList.contains(expectedGame));
			// MatcherAssert.assertThat(gameList.size(), is(8));
			// MatcherAssert.assertThat(gameList, is(expectedGames));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}

}
