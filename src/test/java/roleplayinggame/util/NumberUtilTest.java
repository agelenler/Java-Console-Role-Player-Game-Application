package test.java.roleplayinggame.util;

import java.security.SecureRandom;

import org.junit.Assert;
import org.junit.Test;

/**
 * Number related utility operations
 * 
 * @author Ali Gelenler
 *
 */
public class NumberUtilTest {

	/**
	 * Generate a secure random integer
	 * 
	 * @return int
	 */
	@Test
	public void generateSecureRandom() {
		SecureRandom number = new SecureRandom();
		int rand = number.nextInt();
		Assert.assertTrue(new Integer(rand) instanceof Integer);
	}

}
