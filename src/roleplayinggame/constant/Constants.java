package roleplayinggame.constant;

import java.util.Locale;
import java.util.Properties;

import roleplayinggame.util.ResourceUtil;

/**
 * The Constants class holds the constants including property and data file's path, user direction messages and application constants.
 * 
 * @author Ali Gelenler
 *
 */
public class Constants {

	private static final String GAME_SUBJECT;
	public static final int GAME_BORDERS_X;
	public static final int GAME_BORDERS_Y;
	// Property Files
	private static final String GAME_INFO_PROP_FILE = "data/game_info.properties";
	public static final String GAME_CHARACTER_PROP_FILE = "data/game_characters.properties";
	public static final String GAME_ENEMY_PROP_FILE = "data/game_enemy_characters.properties";
	public static final String PERSISTENCE_TYPE_FILE = "persistenceType.properties";
	public static final String PERSISTENCE_TYPE_NAME = "persistenceType";

	public static final String FILE_PERSISTENCE = "FILE";

	// App Constants
	public static final String SPACE = " ";
	public static final String HYPHEN = "-";
	public static final String DOT = ".";
	public static final String FILE_DELIMITER = "	";
	public static final String FILE_DATA_DELIMITER = ";";
	public static final String GAME_CHARACTER_NAME_PATTERN = "CHARACTER_";
	public static final String UNDERSCORE = "_";
	public static final String ARROW = " -> ";
	public static final String SPACE_FOR_MAP = "       ";
	public static final String QUESTION_MARK_SPLIT = "\\?";
	public static final String OK = "OK";
	public static final String GET = "get";
	public static final String SET = "set";
	public static final String YES_OR_NO = "yes/no";

	static {
		Properties gameInfoProp = ResourceUtil.readPropFile(GAME_INFO_PROP_FILE);
		GAME_SUBJECT = gameInfoProp.getProperty("GAME_SUBJECT");
		GAME_BORDERS_X = Integer.parseInt(gameInfoProp.getProperty("BORDERS_X"));
		GAME_BORDERS_Y = Integer.parseInt(gameInfoProp.getProperty("BORDERS_Y"));
	}

	// Messages
	public static final String WELCOME_MSG = "Welcome to \"" + GAME_SUBJECT + "\" role playing game!";
	public static final String PASS = "pass";

	// Data files
	public static final String FILE_GAME_QUESTIONS = "data/game_questions.data";
	public static final String FILE_GAME_PLAYERS = "data/game_players.data";
	public static final String FILE_SAVED_GAMES = "data/saved_games.data";
	public static final String FILE_SAVED_GAMES_HEADERS = "GAME_ID	USERNAME	CHARACTER_ID	LAST_POSITION_X	LAST_POSITION_Y	POINT	BORDERS_X	BORDERS_Y";

	public static final Locale DEFAULT_LOCALE = Locale.US;
	public static final String ENCODING = "UTF-8";
	public static final String PWD_PADDER = "0";

	public static final String EXIT = "0";
	public static final String MENU_CHOOSE_1 = "1";
	public static final String MENU_CHOOSE_2 = "2";
	public static final String NO_MOVE = "0";
	public static final String DEFAULT_VAL = "-1";
	public static final String MOVE_RIGTH = "6";
	public static final String MOVE_LEFT = "4";
	public static final String MOVE_UP = "8";
	public static final String MOVE_DOWN = "2";

	// User directions
	public static final String ENTER_NAME = "Enter name";
	public static final String ENTER_SURNAME = "Enter surname";
	public static final String ENTER_USERNAME = "Enter username";
	public static final String ENTER_PASSWORD = "Enter password";
	public static final String WRONG_INFO_TRY_AGAIN = "Wrong Information! Try Again";
	public static final String LOGIN_INFO = "Press 1 for sign in or press 2 for sign up, you can go to previous menu or exit anytime by pressing 0!";
	public static final String START_GAME = "Press 1 for new game, 2 to load a game!";
	public static final String CHOOSE_CHARACTER = "Choose a character from the below list!";
	public static final String ENCOUNTERED_ENEMY = "Encountered with \"{0}\", to win the fight answer the following question!";
	public static final String ANSWER_THE_QUESTION = "Answer the question by \"yes\" or \"no\" to continue or pass by typing \"pass\"";
	public static final String WIN_THE_FIGHT = "You win the fight, congratulations, your new experience point is {0}";
	public static final String LOST_THE_FIGHT = "You lost the fight, correct answer is \"{0}\"";
	public static final String MOVE_OPTIONS = "Press 8 + ENTER for UP, 2 + ENTER for DOWN, 6 + ENTER for RIGHT, 4 + ENTER for LEFT";
	public static final String OUT_OF_BORDER = "Out of borders!";
	public static final String ARE_YOU_SURE_EXIT = "Are you sure to exit? yes/no";
	public static final String SAVE_THE_GAME = "Save the game before exit? yes/no";
	public static final String YES = "yes";
	public static final String NO = "no";
	public static final String GAME_SAVED = "Game saved successfully";
	public static final String LOAD_GAME = "Choose a game from the below list to load (Type or copy paste the Game number at the beginning of the line)!";
	public static final String EXPERIENCE_POINT = "Experience point = ";
	public static final String NO_SAVED_GAME = "You don't have any saved game!";
	public static final String GOODBYE_MSG = "Thank you for playing the game, goodbye :)";

}
