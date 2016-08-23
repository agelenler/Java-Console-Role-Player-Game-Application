package roleplayinggame.helper;

import static roleplayinggame.util.PrintUtil.print;
import static roleplayinggame.util.PrintUtil.println;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import roleplayinggame.constant.Constants;
import roleplayinggame.dao.DaoFactory;
import roleplayinggame.exception.RolePlayingException;
import roleplayinggame.exception.RolePlayingExceptionMessages;
import roleplayinggame.model.Experience;
import roleplayinggame.model.Game;
import roleplayinggame.model.GameCharacter;
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
public class RolePlayingGameServiceHelper implements IRolePlayingGameServiceHelper {

	private RolePlayingGameServiceHelper() {

	}

	private static class LazyRolePlayingServiceHelperHolder {
		// Thread-safe without synchronization using static and final
		private static final IRolePlayingGameServiceHelper INSTANCE = new RolePlayingGameServiceHelper();
	}

	public static IRolePlayingGameServiceHelper getInstance() {
		// Lazy creation of Singleton
		return LazyRolePlayingServiceHelperHolder.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see roleplayinggame.helper.IRolePlayingGameServiceHelper#newGame(java.util.Scanner)
	 */
	@Override
	public List<Game> newGame(Scanner sn) throws RolePlayingException {
		println(Constants.CHOOSE_CHARACTER);
		String choosenCharacter = Constants.DEFAULT_VAL;
		GameCharacter currentCharacter = null;
		do {
			for (GameCharacter character : GameData.gameCharacters) {
				println(character.getCharacterId() + Constants.ARROW + character.getName());
			}
			choosenCharacter = sn.next();
			if (GenericHelper.goPrevMenu(choosenCharacter))
				return null;
			int indx = GenericHelper.parseStringToInt(choosenCharacter);
			if (indx != -1 && indx <= GameData.gameCharacters.size())
				currentCharacter = GameData.gameCharacters.get(indx - 1);
			if (currentCharacter == null) {
				println(Constants.WRONG_INFO_TRY_AGAIN);
				println(Constants.CHOOSE_CHARACTER);
			}
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
		return moveAndPlay(bordersX, bordersY, currentCharacter, game, null, sn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see roleplayinggame.helper.IRolePlayingGameServiceHelper#moveAndPlay(int[][], roleplayinggame.model.GameCharacter, roleplayinggame.model.Game, java.util.List, java.util.Scanner)
	 */
	@Override
	public List<Game> moveAndPlay(int bordersX, int bordersY, GameCharacter currentCharacter, Game game, List<Game> games, Scanner sn) throws RolePlayingException {
		int[][] randomEnemies = getRandomEnemyPoints(bordersX, bordersY);

		String direction = Constants.NO_MOVE;
		do {
			Position tmpPosition = currentCharacter.getPosition();
			boolean move = determineTheNewPosition(tmpPosition, direction, bordersX, bordersY);

			if (move || direction.equals(Constants.NO_MOVE)) {
				move(currentCharacter, tmpPosition, bordersX, bordersY);

				if (randomEnemies[tmpPosition.getLastPositionX()][tmpPosition.getLastPositionY()] == 1) {
					fightAgainstAnEnemy(sn, currentCharacter);
					randomEnemies[tmpPosition.getLastPositionX()][tmpPosition.getLastPositionY()] = 0;
					if (tmpPosition.getLastPositionX() + 1 < bordersX && tmpPosition.getLastPositionY() + 1 < bordersY)
						randomEnemies[tmpPosition.getLastPositionX() + 1][tmpPosition.getLastPositionY() + 1] = 1;
					if (tmpPosition.getLastPositionX() - 1 >= 0 && tmpPosition.getLastPositionY() - 1 >= 0)
						randomEnemies[tmpPosition.getLastPositionX() - 1][tmpPosition.getLastPositionY() - 1] = 1;
				}

				println(Constants.MOVE_OPTIONS);
			} else
				println(Constants.OUT_OF_BORDER + Constants.SPACE + Constants.MOVE_OPTIONS);

			direction = sn.nextLine();
			if (StringUtil.isEmpty(direction))
				direction = sn.nextLine();
			if (direction.equals(Constants.NO_MOVE)) {
				String sure = "";
				do {
					println(Constants.ARE_YOU_SURE_EXIT);
					sure = sn.nextLine();
					if (Constants.NO.equalsIgnoreCase(sure)) {
						println(Constants.MOVE_OPTIONS);
						direction = sn.nextLine();
					}
				} while (!(Constants.YES.equalsIgnoreCase(sure) || Constants.NO.equalsIgnoreCase(sure)));
			}
		} while (!direction.equals(Constants.NO_MOVE));

		return saveTheGame(games, game, sn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see roleplayinggame.helper.IRolePlayingGameServiceHelper#loadGame(java.util.List, java.util.Scanner)
	 */
	@Override
	public Game loadGame(List<Game> games, Scanner sn) throws RolePlayingException {
		println(Constants.LOAD_GAME);
		int choosenGame = -1;
		Game loadGame = null;
		boolean found = false;
		do {
			for (Game game : games) {
				if (game.getPlayer().getUsername().equals(GameData.currentPlayer.getUsername())) {
					found = true;
					println(game.getGameId() + Constants.ARROW + GameData.gameCharacters.get(game.getGameCharacter().getCharacterId() - 1).getName() + Constants.ARROW + Constants.EXPERIENCE_POINT + game.getGameCharacter().getExperience().getPoint());
				}
			}
			if (!found) {
				println(Constants.NO_SAVED_GAME);
				return null;
			}
			choosenGame = sn.nextInt();
			for (Game game : games) {
				if (game.getGameId() == choosenGame) {
					loadGame = game;
					break;
				}
			}
			if (choosenGame == 0)
				return null;
			if (loadGame == null)
				println(Constants.WRONG_INFO_TRY_AGAIN + Constants.SPACE + Constants.LOAD_GAME);
		} while (loadGame == null);

		return loadGame;
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
	private List<Game> saveTheGame(List<Game> games, Game game, Scanner sn) throws RolePlayingException {
		String save = "";
		do {
			println(Constants.SAVE_THE_GAME);
			save = sn.nextLine();
			if (Constants.YES.equalsIgnoreCase(save)) {
				boolean result = saveTheGame(games, game);
				if (!result)
					throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0000);
				else {
					println(Constants.GAME_SAVED);
					return DaoFactory.DEFAULT.getGameDao().readFileIntoList(Game.class, Constants.FILE_SAVED_GAMES);
				}
			}
		} while (!(Constants.YES.equalsIgnoreCase(save) || Constants.NO.equalsIgnoreCase(save)));
		return null;
	}

	/**
	 * When encountered a random enemy, fight against it by answering a question about the story
	 * 
	 * @param sn
	 * @param currentCharacter
	 */
	private void fightAgainstAnEnemy(Scanner sn, GameCharacter currentCharacter) {
		GameCharacter enemy = GameData.enemies.get(new Random().nextInt(GameData.enemies.size()));
		println(GenericHelper.formatMessage(Constants.ENCOUNTERED_ENEMY, enemy.getName()));
		String[] questionAndAnswer = GameData.questions.get(new Random().nextInt(GameData.questions.size())).split(Constants.QUESTION_MARK_SPLIT);
		println(questionAndAnswer[0].trim() + Constants.SPACE + Constants.YES_OR_NO);
		String answer = sn.nextLine();
		while (StringUtil.isEmpty(answer) || answer.equals(Constants.MOVE_RIGTH) || answer.equals(Constants.MOVE_LEFT) || answer.equals(Constants.MOVE_UP) || answer.equals(Constants.MOVE_DOWN)
				|| (!Constants.PASS.equalsIgnoreCase(answer) && !Constants.YES.equalsIgnoreCase(answer) && !Constants.NO.equalsIgnoreCase(answer))) {
			println(Constants.ANSWER_THE_QUESTION);
			answer = sn.nextLine();
		}
		if (questionAndAnswer[1].trim().toLowerCase(Constants.DEFAULT_LOCALE).contains(answer.toLowerCase(Constants.DEFAULT_LOCALE))) {
			currentCharacter.getExperience().setPoint(currentCharacter.getExperience().getPoint() + 1);
			println(GenericHelper.formatMessage(Constants.WIN_THE_FIGHT, String.valueOf(currentCharacter.getExperience().getPoint())));
		} else
			println(GenericHelper.formatMessage(Constants.LOST_THE_FIGHT, questionAndAnswer[1].trim()));
	}

	/**
	 * Move on the map according to player directions
	 * 
	 * @param currentCharacter
	 * @param tmpPosition
	 * @param borders
	 */
	private void move(GameCharacter currentCharacter, Position tmpPosition, int bordersX, int bordersY) {
		String tmpName = currentCharacter.getName();
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
		for (int i = 0; i < bordersY; i++) {
			for (int j = 0; j < bordersX; j++) {
				if (tmpPosition.getLastPositionX() == j && tmpPosition.getLastPositionY() == i) {
					print(Constants.SPACE_FOR_MAP + visibleName);
				} else {
					print(Constants.SPACE_FOR_MAP);
					for (int j2 = 0; j2 < len; j2++) {
						print(Constants.UNDERSCORE);
					}
				}
				print(Constants.SPACE_FOR_MAP);
			}
			println();
			println();
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
	private boolean determineTheNewPosition(Position tmpPosition, String direction, int bordersX, int bordersY) {
		boolean move = false;
		if (direction.equals(Constants.MOVE_RIGTH)) {
			if (tmpPosition.getLastPositionX() + 1 < bordersX) {
				tmpPosition.setLastPositionX(tmpPosition.getLastPositionX() + 1);
				move = true;
			}
		} else if (direction.equals(Constants.MOVE_LEFT)) {
			if (tmpPosition.getLastPositionX() > 0) {
				tmpPosition.setLastPositionX(tmpPosition.getLastPositionX() - 1);
				move = true;
			}
		} else if (direction.equals(Constants.MOVE_UP)) {
			if (tmpPosition.getLastPositionY() > 0) {
				tmpPosition.setLastPositionY(tmpPosition.getLastPositionY() - 1);
				move = true;
			}
		} else if (direction.equals(Constants.MOVE_DOWN)) {
			if (tmpPosition.getLastPositionY() + 1 < bordersY) {
				tmpPosition.setLastPositionY(tmpPosition.getLastPositionY() + 1);
				move = true;
			}
		}
		return move;
	}

	/**
	 * Create random enemies on some points on 2-D matrix map
	 * 
	 * @param borders
	 * @return
	 */
	private int[][] getRandomEnemyPoints(int bordersX, int bordersY) {
		int[][] randomEnemies = new int[bordersX][bordersY];
		for (int i = 0; i < bordersY; i++) {
			for (int j = 0; j < bordersX; j++) {
				if (i > 0 && j > 0)
					randomEnemies[j][i] = new Random().nextInt() > 0 ? 1 : 0;
			}
		}
		return randomEnemies;
	}

	/**
	 * Save the game and return a boolean indicating the success.
	 * 
	 * @param games
	 * @param game
	 * @return
	 * @throws RolePlayingException
	 */
	private boolean saveTheGame(List<Game> games, Game game) throws RolePlayingException {
		boolean result = false;
		if (games == null) {
			result = DaoFactory.DEFAULT.getGameDao().writeDataIntoFile(game, Constants.FILE_SAVED_GAMES, true);
		} else {
			result = true;
			boolean tmpResult = DaoFactory.DEFAULT.getGameDao().writeTextIntoFile(Constants.FILE_SAVED_GAMES_HEADERS, Constants.FILE_SAVED_GAMES, false);
			if (!tmpResult)
				result = false;
			for (Game tmpGame : games) {
				tmpResult = DaoFactory.DEFAULT.getGameDao().writeDataIntoFile(tmpGame, Constants.FILE_SAVED_GAMES, true);
				if (!tmpResult)
					result = false;
			}
		}
		return result;
	}

}
