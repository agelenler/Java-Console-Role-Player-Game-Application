package test.java.roleplayinggame.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import roleplayinggame.constant.Constants;
import roleplayinggame.dao.DaoFactory;
import roleplayinggame.exception.RolePlayingException;
import roleplayinggame.helper.GenericHelper;
import roleplayinggame.model.Experience;
import roleplayinggame.model.Game;
import roleplayinggame.model.GameCharacter;
import roleplayinggame.model.Player;
import roleplayinggame.model.Position;
import roleplayinggame.staticdata.GameData;
import roleplayinggame.util.NumberUtil;
import roleplayinggame.util.StringUtil;

/**
 * Game service helper class
 * 
 * @author Ali Gelenler
 *
 */
public class RolePlayingGameServiceHelperTest {

	private String expectedChoosenCharacter;
	private int bordersX;
	private int bordersY;
	private int[][] expectedRandomEnemies = null;
	private GameCharacter expectedCurrentCharacter = null;
	private Experience expectedExperience = null;
	private Position expectedPosition = null;
	private String expectedDirection = null;
	private String expectedDirectionNoQuit = null;
	private boolean expectedMove;
	private String sureExit = null;
	private List<Game> expectedGames = null;
	private Game expectedGame = null;
	private int expectedChoosenGame;
	private String exptectedSave;
	private String expectedAnswer;
	private String expectedAnswerPass;
	private int currentPoint;
	private String expectedVisibleName;
	private String expectedLoadGamePrintStr;

	@Before
	public void initObjects() {
		expectedChoosenCharacter = "1";
		bordersX = 6;
		bordersY = 6;
		expectedRandomEnemies = new int[bordersX][bordersY];
		expectedRandomEnemies[1][2] = 1;
		expectedRandomEnemies[3][5] = 1;
		expectedRandomEnemies[1][1] = 1;
		expectedCurrentCharacter = new GameCharacter();
		expectedCurrentCharacter.setCharacterId(1);
		expectedCurrentCharacter.setName("Walter Bishop");
		expectedExperience = new Experience(2);
		expectedCurrentCharacter.setExperience(expectedExperience);
		expectedPosition = new Position(2, 4);
		expectedCurrentCharacter.setPosition(expectedPosition);
		expectedDirection = "6";
		expectedMove = true;
		sureExit = "yes";
		expectedDirectionNoQuit = "4";
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
		expectedChoosenGame = 1664596199;
		exptectedSave = "yes";
		expectedAnswer = "yes";
		expectedAnswer = "pass";
		currentPoint = expectedCurrentCharacter.getExperience().getPoint();
		expectedVisibleName = "W. Bishop";
		expectedLoadGamePrintStr = "1664596199 -> Walter Bishop -> Experience point = 8";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see roleplayinggame.helper.IRolePlayingGameServiceHelper#newGame(java.util. Scanner)
	 */
	@Test
	public void newGame() throws RolePlayingException {
		// println(Constants.CHOOSE_CHARACTER);
		String choosenCharacter = Constants.DEFAULT_VAL;
		GameCharacter currentCharacter = null;
		do {
			// for (GameCharacter character : GameData.gameCharacters) {
			// println(character.getCharacterId() + Constants.ARROW + character.getName());
			// }
			choosenCharacter = expectedChoosenCharacter;
			int indx = GenericHelper.parseStringToInt(choosenCharacter);
			if (indx != -1 && indx <= GameData.gameCharacters.size())
				currentCharacter = GameData.gameCharacters.get(indx - 1);

			Assert.assertNotNull(currentCharacter);

			Assert.assertEquals(currentCharacter.getCharacterId(), expectedCurrentCharacter.getCharacterId());

		} while (currentCharacter == null);

		Game game = new Game();
		game.setGameId(NumberUtil.generateSecureRandom());
		game.setPlayer(GameData.currentPlayer);
		Position position = new Position(0, 0);
		currentCharacter.setPosition(position);
		currentCharacter.setExperience(new Experience(0));
		game.setGameCharacter(currentCharacter);
		int bordersX = Constants.GAME_BORDERS_X;
		int bordersY = Constants.GAME_BORDERS_Y;
		game.setBordersX(bordersX);
		game.setBordersY(bordersY);
		moveAndPlay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see roleplayinggame.helper.IRolePlayingGameServiceHelper#moveAndPlay(int[][], roleplayinggame.model.GameCharacter, roleplayinggame.model.Game, java.util.List, java.util.Scanner)
	 */
	@Test
	public void moveAndPlay() throws RolePlayingException {
		int[][] randomEnemies = expectedRandomEnemies;

		String direction = Constants.NO_MOVE;
		do {
			Position tmpPosition = expectedCurrentCharacter.getPosition();
			boolean move = expectedMove;

			if (move || direction.equals(Constants.NO_MOVE)) {

				Assert.assertTrue(move);

				move();

				if (randomEnemies[tmpPosition.getLastPositionX()][tmpPosition.getLastPositionY()] == 1)
					fightAgainstAnEnemy();

				// println(Constants.MOVE_OPTIONS);
			}
			// else
			// println(Constants.OUT_OF_BORDER + Constants.SPACE + Constants.MOVE_OPTIONS);

			if (StringUtil.isEmpty(direction))
				direction = expectedDirection;
			if (direction.equals(Constants.NO_MOVE)) {
				// println(Constants.ARE_YOU_SURE_EXIT);
				String sure = sureExit;
				if (Constants.NO.equalsIgnoreCase(sure)) {
					// println(Constants.MOVE_OPTIONS);
					direction = expectedDirectionNoQuit;
				}
			}
		} while (!direction.equals(Constants.NO_MOVE));

		saveTheGame();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see roleplayinggame.helper.IRolePlayingGameServiceHelper#loadGame(java.util. List, java.util.Scanner)
	 */
	@Test
	public void loadGame() throws RolePlayingException {
		// println(Constants.LOAD_GAME);
		int choosenGame = -1;
		Game loadGame = null;
		do {
			Assert.assertTrue(expectedGames.contains(expectedGame));

			for (Game game : expectedGames) {
				if (game.getPlayer().getUsername().equals(expectedGame.getPlayer().getUsername())) {
					String printStr = game.getGameId() + Constants.ARROW + GameData.gameCharacters.get(game.getGameCharacter().getCharacterId() - 1).getName() + Constants.ARROW + Constants.EXPERIENCE_POINT + game.getGameCharacter().getExperience().getPoint();
					// println(game.getGameId() + Constants.ARROW + GameData.gameCharacters.get(game.getGameCharacter().getCharacterId() - 1).getName() + Constants.ARROW + Constants.EXPERIENCE_POINT + game.getGameCharacter().getExperience().getPoint());
					Assert.assertEquals(printStr, expectedLoadGamePrintStr);
				}
			}
			choosenGame = expectedChoosenGame;
			for (Game game : expectedGames) {
				if (game.getGameId() == choosenGame) {
					loadGame = game;
					break;
				}
			}

			Assert.assertNotNull(loadGame);

		} while (loadGame == null);
	}

	/**
	 * Save the game and return the updated game list
	 * 
	 * @param games
	 * @param game
	 * @param sn
	 * @return List<Game>
	 * @throws RolePlayingException
	 */
	@Test
	public void saveTheGame() throws RolePlayingException {
		// println(Constants.SAVE_THE_GAME);
		String save = exptectedSave;
		if (Constants.YES.equalsIgnoreCase(save)) {
			saveTheGame2();
			// println(Constants.GAME_SAVED);
			List<Game> games = DaoFactory.DEFAULT.getGameDao().readFileIntoList(Game.class, Constants.FILE_SAVED_GAMES);

			Assert.assertTrue(games.contains(expectedGame));

		}
	}

	/**
	 * When encountered a random enemy, fight against it by answering a question about the story
	 * 
	 * @param sn
	 * @param currentCharacter
	 */
	@Test
	public void fightAgainstAnEnemy() {
		GameCharacter enemy = GameData.enemies.get(new Random().nextInt(GameData.enemies.size()));
		// println(GenericHelper.formatMessage(Constants.ENCOUNTERED_ENEMY, enemy.getName()));
		String[] questionAndAnswer = GameData.questions.get(new Random().nextInt(GameData.questions.size())).split(Constants.QUESTION_MARK_SPLIT);
		// println(questionAndAnswer[0].trim() + Constants.SPACE + Constants.YES_OR_NO);
		String answer = expectedAnswer;
		while (StringUtil.isEmpty(answer) || answer.equals(Constants.MOVE_RIGTH) || answer.equals(Constants.MOVE_LEFT) || answer.equals(Constants.MOVE_UP) || answer.equals(Constants.MOVE_DOWN)
				|| (!Constants.PASS.equalsIgnoreCase(answer) && !Constants.YES.equalsIgnoreCase(answer) && !Constants.NO.equalsIgnoreCase(answer))) {
			// println(Constants.ANSWER_THE_QUESTION);
			answer = expectedAnswerPass;
		}
		if (questionAndAnswer[1].trim().toLowerCase(Constants.DEFAULT_LOCALE).equalsIgnoreCase(answer.toLowerCase(Constants.DEFAULT_LOCALE))) {
			expectedCurrentCharacter.getExperience().setPoint(expectedCurrentCharacter.getExperience().getPoint() + 1);
			// println(GenericHelper.formatMessage(Constants.WIN_THE_FIGHT, String.valueOf(expectedCurrentCharacter.getExperience().getPoint())));
		}
		// else
		// println(GenericHelper.formatMessage(Constants.LOST_THE_FIGHT, questionAndAnswer[1].trim()));

		if (answer.equals(Constants.PASS))
			Assert.assertEquals(currentPoint, expectedCurrentCharacter.getExperience().getPoint());
	}

	/**
	 * Move on the map according to player directions
	 * 
	 * @param currentCharacter
	 * @param tmpPosition
	 * @param borders
	 */
	@Test
	public void move() {
		String tmpName = expectedCurrentCharacter.getName();
		String[] nameArr = tmpName.split(Constants.SPACE);
		int len = 0;
		String visibleName = null;
		if (nameArr.length == 1) {
			len = tmpName.length();
			visibleName = tmpName;
		} else {
			visibleName = String.valueOf(nameArr[0].charAt(0)) + Constants.DOT;
			for (int i = 1; i < nameArr.length; i++) {
				visibleName += Constants.SPACE + nameArr[i];
			}
			len = visibleName.length();
		}

		Assert.assertEquals(visibleName, expectedVisibleName);

		for (int i = 0; i < bordersY; i++) {
			for (int j = 0; j < bordersX; j++) {
				if (expectedCurrentCharacter.getPosition().getLastPositionX() == j && expectedCurrentCharacter.getPosition().getLastPositionY() == i) {
					// print(Constants.SPACE_FOR_MAP + visibleName);
				} else {
					// print(Constants.SPACE_FOR_MAP);
					for (int j2 = 0; j2 < len; j2++) {
						// print(Constants.UNDERSCORE);
					}
				}
				// print(Constants.SPACE_FOR_MAP);
			}
			// println();
			// println();
		}
	}

	/**
	 * Determine the new position according to player directions
	 * 
	 * @param tmpPosition
	 * @param direction
	 * @param borders
	 * @return boolean
	 */
	@Test
	public void determineTheNewPosition() {
		boolean move = false;
		if (expectedDirection.equals(Constants.MOVE_RIGTH)) {
			if (expectedPosition.getLastPositionX() + 1 < bordersX) {
				expectedPosition.setLastPositionX(expectedPosition.getLastPositionX() + 1);
				move = true;
			}
		} else if (expectedDirection.equals(Constants.MOVE_LEFT)) {
			if (expectedPosition.getLastPositionX() > 0) {
				expectedPosition.setLastPositionX(expectedPosition.getLastPositionX() - 1);
				move = true;
			}
		} else if (expectedDirection.equals(Constants.MOVE_UP)) {
			if (expectedPosition.getLastPositionY() > 0) {
				expectedPosition.setLastPositionY(expectedPosition.getLastPositionY() - 1);
				move = true;
			}
		} else if (expectedDirection.equals(Constants.MOVE_DOWN)) {
			if (expectedPosition.getLastPositionY() + 1 < bordersY) {
				expectedPosition.setLastPositionY(expectedPosition.getLastPositionY() + 1);
				move = true;
			}
		}

		Assert.assertTrue(move);

	}

	/**
	 * Create random enemies on some points on 2-D matrix map
	 * 
	 * @param borders
	 * @return
	 */
	@Test
	public void getRandomEnemyPoints() {
		int[][] randomEnemies = new int[bordersX][bordersY];
		for (int i = 0; i < bordersY; i++) {
			for (int j = 0; j < bordersX; j++) {
				if (i > 0 && j > 0)
					randomEnemies[j][i] = new Random().nextInt() > 0 ? 1 : 0;
			}
		}

		Assert.assertTrue(randomEnemies.length == bordersX);
		Assert.assertTrue(randomEnemies[0].length == bordersY);
	}

	/**
	 * Save the game and return a boolean indicating the success.
	 * 
	 * @param games
	 * @param game
	 * @return
	 * @throws RolePlayingException
	 */
	@Test
	public void saveTheGame2() throws RolePlayingException {
		boolean result = false;
		if (expectedGames == null) {
			result = DaoFactory.DEFAULT.getGameDao().writeDataIntoFile(expectedGame, Constants.FILE_SAVED_GAMES, true);
		} else {
			result = true;
			boolean tmpResult = DaoFactory.DEFAULT.getGameDao().writeTextIntoFile(Constants.FILE_SAVED_GAMES_HEADERS, Constants.FILE_SAVED_GAMES, false);
			if (!tmpResult)
				result = false;
			for (Game tmpGame : expectedGames) {
				tmpResult = DaoFactory.DEFAULT.getGameDao().writeDataIntoFile(tmpGame, Constants.FILE_SAVED_GAMES, true);
				if (!tmpResult)
					result = false;
			}
		}

		List<Game> games = DaoFactory.DEFAULT.getGameDao().readFileIntoList(Game.class, Constants.FILE_SAVED_GAMES);

		Assert.assertTrue(games.contains(expectedGame));
		Assert.assertTrue(result);

	}

}
