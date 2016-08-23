package roleplayinggame.staticdata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roleplayinggame.constant.Constants;
import roleplayinggame.model.GameCharacter;
import roleplayinggame.model.Player;
import roleplayinggame.util.ResourceUtil;

/**
 * The GameData class holds the static data of the app
 * 
 * @author Ali Gelenler
 *
 */
public abstract class GameData {

	public static final Logger logger = LoggerFactory.getLogger(GameData.class);

	public static Player currentPlayer = null;
	public static List<GameCharacter> gameCharacters = new ArrayList<>();
	public static List<GameCharacter> enemies = new ArrayList<>();
	public static List<String> questions = new ArrayList<>();

	static {
		try {
			Properties gameCharacterProp = ResourceUtil.readPropFile(Constants.GAME_CHARACTER_PROP_FILE);
			int i = 1;
			String gameCharacter = null;
			do {
				gameCharacter = gameCharacterProp.getProperty(Constants.GAME_CHARACTER_NAME_PATTERN + i++);
				if (gameCharacter != null) {
					String[] characterData = gameCharacter.split(Constants.FILE_DATA_DELIMITER);
					GameCharacter character = new GameCharacter();
					character.setCharacterId(Integer.parseInt(characterData[0]));
					character.setName(characterData[1]);
					gameCharacters.add(character);
				}
			} while (gameCharacter != null);

			Properties enemiesProp = ResourceUtil.readPropFile(Constants.GAME_ENEMY_PROP_FILE);
			i = 1;
			String enemy = null;
			do {
				enemy = enemiesProp.getProperty(Constants.GAME_CHARACTER_NAME_PATTERN + i++);
				if (enemy != null) {
					String[] characterData = enemy.split(Constants.FILE_DATA_DELIMITER);
					GameCharacter character = new GameCharacter();
					character.setCharacterId(Integer.parseInt(characterData[0]));
					character.setName(characterData[1]);
					enemies.add(character);
				}
			} while (enemy != null);

			questions = Files.lines(Paths.get(Constants.FILE_GAME_QUESTIONS)).collect(Collectors.toList());
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
