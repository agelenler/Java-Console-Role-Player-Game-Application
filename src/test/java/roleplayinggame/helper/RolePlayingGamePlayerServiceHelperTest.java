package test.java.roleplayinggame.helper;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import roleplayinggame.constant.Constants;

/**
 * Game player service helper class.
 * 
 * @author AGELENLER
 *
 */
public class RolePlayingGamePlayerServiceHelperTest {

	private String input;
	private String result;

	@Before
	public void initObjects() {
		input = "1234ab";
		result = "MTIzNGFi";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see roleplayinggame.helper.IRolePlayingGamePlayerServiceHelper#getEncodedPwd( java.lang.String)
	 */
	@Test
	public void getEncodedPwd() throws UnsupportedEncodingException {
		try {
			String password = null;
			byte[] pwdByte = input.getBytes(Constants.ENCODING);
			password = new String(Base64.getEncoder().encodeToString(pwdByte.length >= 2 ? pwdByte : (new String(pwdByte) + Constants.PWD_PADDER).getBytes(Constants.ENCODING)));
			Assert.assertEquals(result, password);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
