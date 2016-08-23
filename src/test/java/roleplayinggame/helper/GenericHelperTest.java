package test.java.roleplayinggame.helper;

import java.text.MessageFormat;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import roleplayinggame.constant.Constants;

/**
 * Abstract GenericHelper holds the static helper methods that is useful and be used from any of the service objects.
 * 
 * @author Ali Gelenler
 *
 */
public class GenericHelperTest {

	private String input;
	private String inputFormatted;
	private Object[] param;
	private String inputFormattedExpected;
	private boolean result;
	private Collection<?> col;
	private String strToInt = "2";
	private int expectedInt = 2;

	@Before
	public void initObjects() {
		input = Constants.EXIT;
		inputFormatted = Constants.LOST_THE_FIGHT;
		param = new Object[] { "answer" };
		inputFormattedExpected = "You lost the fight, correct answer is \"answer\"";
	}

	@Test
	public void goPrevMenu() {
		if (Constants.EXIT.equals(input))
			result = true;
		else
			result = false;

		if (Constants.EXIT.equals(input))
			Assert.assertTrue(result);
	}

	public void checkExit() {
		if (Constants.EXIT.equals(input))
			System.exit(0);
	}

	@Test
	public void formatMessage() {
		MessageFormat mf = new MessageFormat(inputFormatted);
		Assert.assertTrue(inputFormattedExpected.equals(mf.format(param)));
	}

	@Test
	public void isEmpty() {
		if (col == null || col.size() == 0)
			result = true;
		else
			result = false;

		if (col == null || col.size() == 0)
			Assert.assertTrue(result);
	}

	@Test
	public void parseStringToInt() {
		Assert.assertEquals(expectedInt, Integer.parseInt(strToInt));
	}

}
