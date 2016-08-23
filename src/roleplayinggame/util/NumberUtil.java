package roleplayinggame.util;

import java.security.SecureRandom;

/**
 * Number related utility operations
 * 
 * @author Ali Gelenler
 *
 */
public abstract class NumberUtil {

	/**
	 * Generate a secure random integer
	 * 
	 * @return int
	 */
	public static int generateSecureRandom() {
		SecureRandom number = new SecureRandom();
		int rand = number.nextInt();
		return rand > 0 ? rand : -1 * rand;
	}

}
