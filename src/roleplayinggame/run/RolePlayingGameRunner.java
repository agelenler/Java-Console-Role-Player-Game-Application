package roleplayinggame.run;

import static roleplayinggame.util.PrintUtil.println;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roleplayinggame.constant.Constants;
import roleplayinggame.exception.RolePlayingException;
import roleplayinggame.exception.RolePlayingExceptionMessages;
import roleplayinggame.service.RolePlayingGamePlayerService;
import roleplayinggame.service.RolePlayingGameService;
import roleplayinggame.staticdata.GameData;

/**
 * This class has the main method and is the starting point of the Role Playing Game.
 * The application direct the user by command lines. It welcomes the user and choose to sign-in or
 * sign-up with a message "Press 1 for sign in or press 2 for sign up, you can go to previous menu or exit anytime by pressing 0!".
 * New users sign up to the app and the new player information persisted into the data/game_players.data file. Then using username/password
 * pair player authenticated to gain access to the app.
 * After succesfully authenticated, user directed either to start a new game or load a game from the saved games with a message
 * "Press 1 for new game, 2 to load a game!".
 * Then the app directed the player to choose a player from the character list persisted in a file in data/game_characters.properties.
 * After choosing a character, a 2-D matrix like map appears taking the dimensions from a file in data/game_info.properties.
 * Player then directed with the move information message "Press 8 + ENTER for UP, 2 + ENTER for DOWN, 6 + ENTER for RIGHT, 4 + ENTER for LEFT".
 * When the player exploring, some randomly distributed enemies encountered on some points on the map and a question asked to the player to answer
 * and win the fight and to gain experience points.
 * Player can exit by 0 and directed whether wants to save the game or not. If the player wants to save the game, it is persisted to the
 * data/saved_games.data file.
 * At anytime player can go the the upper menu by pressing 0. At the top menu pressing 0 means the player wants to exit the game. 
 * 
 * @author Ali Gelenler
 * @version 1.0
 *
 */
public class RolePlayingGameRunner {

	public static final Logger logger = LoggerFactory.getLogger(RolePlayingGameRunner.class);

	public static void main(String[] args) {
		String userChoose = null;
		Scanner sn = null;
		try {
			sn = new Scanner(System.in);
			do {
				try {
					boolean dontStartGame = false;
					do {
						println(Constants.WELCOME_MSG);
						println(Constants.LOGIN_INFO);
						userChoose = sn.next();
						GameData.currentPlayer = null;
						if (Constants.MENU_CHOOSE_1.equals(userChoose)) {
							dontStartGame = RolePlayingGamePlayerService.getInstance().signIn(sn);
							break;
						} else if (Constants.MENU_CHOOSE_2.equals(userChoose)) {
							dontStartGame = RolePlayingGamePlayerService.getInstance().signUp(sn);
							break;
						} else if (Constants.EXIT.equals(userChoose))
							dontStartGame = true;
					} while (!Constants.EXIT.equals(userChoose));
					if (!dontStartGame)
						RolePlayingGameService.getInstance().startGame(sn);
				} catch (RolePlayingException e) {
					println(e.getMessage());
				}
				try {
					if (Constants.EXIT.equals(userChoose)) {
						println(Constants.ARE_YOU_SURE_EXIT);
						String sure = sn.next();
						if (Constants.NO.equalsIgnoreCase(sure)) {
							println(Constants.LOGIN_INFO);
							userChoose = sn.next();
						} else {
							println(Constants.GOODBYE_MSG);
						}
					}
				} catch (Exception e) {
					logger.error(RolePlayingExceptionMessages.roleplaying_0000.getErrorMessage(), e);
					println(RolePlayingExceptionMessages.roleplaying_0000.getErrorMessage());
				}
			} while (!Constants.EXIT.equals(userChoose));
		} catch (Exception e) {
			logger.error(RolePlayingExceptionMessages.roleplaying_0000.getErrorMessage(), e);
			println(RolePlayingExceptionMessages.roleplaying_0000.getErrorMessage());
		} finally {
			try {
				if (sn != null)
					sn.close();
			} catch (Exception e) {
				logger.error(RolePlayingExceptionMessages.roleplaying_0000.getErrorMessage(), e);
				println(RolePlayingExceptionMessages.roleplaying_0000.getErrorMessage());
			}
		}
	}
}
