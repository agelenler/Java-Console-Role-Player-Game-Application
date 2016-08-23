package roleplayinggame.helper;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import roleplayinggame.constant.Constants;
import roleplayinggame.exception.RolePlayingException;
import roleplayinggame.exception.RolePlayingExceptionMessages;

/**
 * Game player service helper class.
 * 
 * @author AGELENLER
 *
 */
public class RolePlayingGamePlayerServiceHelper implements IRolePlayingGamePlayerServiceHelper {

	private RolePlayingGamePlayerServiceHelper() {

	}

	private static class LazyRolePlayingGamePlayerServiceHelperHolder {
		// Thread-safe without synchronization using static and final
		private static final IRolePlayingGamePlayerServiceHelper INSTANCE = new RolePlayingGamePlayerServiceHelper();
	}

	public static IRolePlayingGamePlayerServiceHelper getInstance() {
		// Lazy creation of Singleton
		return LazyRolePlayingGamePlayerServiceHelperHolder.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see roleplayinggame.helper.IRolePlayingGamePlayerServiceHelper#getEncodedPwd(java.lang.String)
	 */
	@Override
	public String getEncodedPwd(String input) throws RolePlayingException {
		String password = null;
		try {
			byte[] pwdByte = input.getBytes(Constants.ENCODING);
			password = new String(Base64.getEncoder().encodeToString(pwdByte.length >= 2 ? pwdByte : (new String(pwdByte) + Constants.PWD_PADDER).getBytes(Constants.ENCODING)));
		} catch (UnsupportedEncodingException e) {
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0001);
		}
		return password;
	}

}
