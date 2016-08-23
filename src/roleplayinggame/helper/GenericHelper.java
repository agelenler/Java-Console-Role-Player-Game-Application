package roleplayinggame.helper;

import java.text.MessageFormat;
import java.util.Collection;

import roleplayinggame.constant.Constants;

/**
 * Abstract GenericHelper holds the static helper methods that is useful and be used from any of the service objects.
 * 
 * @author Ali Gelenler
 *
 */
public abstract class GenericHelper {

	private GenericHelper() {

	}

	public static boolean goPrevMenu(String input) {
		if (Constants.EXIT.equals(input))
			return true;
		else
			return false;
	}

	public static void checkExit(String input) {
		if (Constants.EXIT.equals(input))
			System.exit(0);
	}

	public static String formatMessage(String input, String... params) {
		MessageFormat mf = new MessageFormat(input);
		return mf.format(params);
	}

	public static boolean isEmpty(Collection<?> col) {
		if (col == null || col.size() == 0)
			return true;
		else
			return false;
	}

	public static int parseStringToInt(String input) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

}
