package roleplayinggame.service;

import static roleplayinggame.util.PrintUtil.println;

import java.util.List;
import java.util.Scanner;

import roleplayinggame.constant.Constants;
import roleplayinggame.dao.DaoFactory;
import roleplayinggame.exception.RolePlayingException;
import roleplayinggame.helper.GenericHelper;
import roleplayinggame.helper.RolePlayingGameServiceHelper;
import roleplayinggame.model.Game;
import roleplayinggame.model.GameCharacter;
import roleplayinggame.staticdata.GameData;

/**
 * RolePlayingGameService holds the game related methods
 * @author Ali Gelenler
 *
 */
public class RolePlayingGameService extends AbstractRolePlayingGameService implements IRolePlayingGameService {

	private RolePlayingGameService() {

	}

	private static class LazyRolePlayingGameServiceHolder {
		// Thread-safe without synchronization using static and final
		private static final IRolePlayingGameService INSTANCE = new RolePlayingGameService();
	}

	public static IRolePlayingGameService getInstance() {
		// Lazy creation of Singleton
		return LazyRolePlayingGameServiceHolder.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * @see roleplayinggame.service.AbstractRolePlayingGameService#startGameExecute(java.util.Scanner)
	 */
	@Override
	public void startGameExecute(Scanner sn) throws RolePlayingException {
		List<Game> games = DaoFactory.DEFAULT.getGameDao().readFileIntoList(Game.class, Constants.FILE_SAVED_GAMES);
		String userChoose = null;
		if (!GenericHelper.isEmpty(games)) {
			z: do {
				println(Constants.START_GAME);
				userChoose = sn.next();
				if (Constants.MENU_CHOOSE_1.equals(userChoose)) {
					List<Game> tmpGameList = RolePlayingGameServiceHelper.getInstance().newGame(sn);
					if (!GenericHelper.isEmpty(tmpGameList))
						games = tmpGameList;
				} else if (Constants.MENU_CHOOSE_2.equals(userChoose)) {
					Game loadGame = RolePlayingGameServiceHelper.getInstance().loadGame(games, sn);
					if (loadGame == null)
						continue z;
					GameCharacter currentCharacter = GameData.gameCharacters.get(loadGame.getGameCharacter().getCharacterId() - 1);
					currentCharacter.setPosition(loadGame.getGameCharacter().getPosition());
					currentCharacter.setExperience(loadGame.getGameCharacter().getExperience());
					List<Game> tmpGameList = RolePlayingGameServiceHelper.getInstance().moveAndPlay(loadGame.getBordersX(), loadGame.getBordersY(), currentCharacter, loadGame, games, sn);
					if (tmpGameList != null)
						games = tmpGameList;
				}
			} while (!Constants.EXIT.equals(userChoose));

		} else {
			List<Game> tmpGameList = RolePlayingGameServiceHelper.getInstance().newGame(sn);
			if (tmpGameList != null)
				games = tmpGameList;
		}
	}

}
