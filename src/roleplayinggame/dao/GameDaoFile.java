package roleplayinggame.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roleplayinggame.constant.Constants;
import roleplayinggame.exception.RolePlayingException;
import roleplayinggame.exception.RolePlayingExceptionMessages;
import roleplayinggame.model.Experience;
import roleplayinggame.model.Game;
import roleplayinggame.model.GameCharacter;
import roleplayinggame.model.Player;
import roleplayinggame.model.Position;
import roleplayinggame.util.StringUtil;

/**
 * The implementation of GameListDao.
 * 
 * @author Ali Gelenler
 *
 */
class GameDaoFile extends GenericDaoFile<Game> implements GameDao {

	public static final Logger logger = LoggerFactory.getLogger(GameDaoFile.class);

	public static final GameDao INSTANCE = new GameDaoFile();

	private GameDaoFile() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see roleplayinggame.dao.GameListDao#getGameListFromFile(java.lang.String)
	 */
	@Override
	public List<Game> getGameListFromFile(String filename) throws RolePlayingException {
		List<Game> gameList = new ArrayList<>();
		try {
			try (Stream<String> stream = Files.lines(Paths.get(filename))) {
				List<String> tmpList = stream.skip(1).collect(Collectors.toList());
				for (String tmpStr : tmpList) {
					if (!StringUtil.isEmpty(tmpStr)) {
						String[] tmpArr = tmpStr.split(Constants.FILE_DELIMITER);
						int gameId = Integer.parseInt(tmpArr[0]);
						Player player = new Player(tmpArr[1]);
						int characterId = Integer.parseInt(tmpArr[2]);
						int positionX = Integer.parseInt(tmpArr[3]);
						int positionY = Integer.parseInt(tmpArr[4]);
						Position position = new Position(positionX, positionY);
						Experience experience = new Experience(Integer.parseInt(tmpArr[5]));
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
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0003);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0003);
		}
		return gameList;
	}

}
