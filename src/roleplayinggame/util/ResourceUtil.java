package roleplayinggame.util;

import static roleplayinggame.util.PrintUtil.println;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roleplayinggame.resources.Resources;

/**
 * Resource related utility operations
 * 
 * @author Ali Gelenler
 *
 */
public abstract class ResourceUtil {

	public static final Logger logger = LoggerFactory.getLogger(ResourceUtil.class);

	/**
	 * Return a properties file from file name
	 * 
	 * @param propFileName
	 * @return Properties
	 */
	public static Properties readPropFile(String propFileName) {
		Properties gameInfoProp = new Properties();
		InputStream input = null;
		try {
			// gameInfoProp.load(Resources.class.getResourceAsStream(propFileName));
			gameInfoProp.load(new FileInputStream(new File(propFileName)));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return gameInfoProp;
	}

	/**
	 * Return a value from a properties file given a file name and the property name
	 * 
	 * @param file
	 * @param name
	 * @return String
	 */
	public static String readFromPropFile(final String file, String name) {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = Resources.class.getResourceAsStream(file);
			if (input == null) {
				println("Dosya bulunamadı " + file);
				return null;
			}
			// load a properties file from class path, inside static method
			prop.load(input);
			// get the property value and print it out
			return prop.getProperty(name);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}

}
