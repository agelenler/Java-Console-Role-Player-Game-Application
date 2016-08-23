package test.java.roleplayinggame.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import roleplayinggame.constant.Constants;
import roleplayinggame.resources.Resources;

/**
 * Resource related utility operations
 * 
 * @author Ali Gelenler
 *
 */
public class ResourceUtilTest {

	private String propFileName;
	private String propFileNameType2;
	private String propertyName;

	@Before
	public void initObjects() {
		propFileName = Constants.GAME_CHARACTER_PROP_FILE;
		propFileNameType2 = Constants.PERSISTENCE_TYPE_FILE;
		propertyName = Constants.PERSISTENCE_TYPE_NAME;
	}

	/**
	 * Return a properties file from file name
	 * 
	 * @param propFileName
	 * @return Properties
	 */
	@Test
	public void readPropFile() {
		Properties gameInfoProp = new Properties();
		InputStream input = null;
		try {
			// gameInfoProp.load(Resources.class.getResourceAsStream(propFileName));
			gameInfoProp.load(new FileInputStream(new File(propFileName)));

			Assert.assertNotNull(gameInfoProp);

		} catch (IOException e) {
			Assert.fail(e.getMessage());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					Assert.fail(e.getMessage());
				}
			}
		}
	}

	/**
	 * Return a value from a properties file given a file name and the property name
	 * 
	 * @param file
	 * @param name
	 * @return String
	 */
	@Test
	public void readFromPropFile() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = Resources.class.getResourceAsStream(propFileNameType2);

			Assert.assertNotNull(input);

			// load a properties file from class path, inside static method
			prop.load(input);

			Assert.assertNotNull(prop.getProperty(propertyName));
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					Assert.fail(e.getMessage());
				}
			}
		}
	}

}
