package roleplayinggame.exception;

import java.io.Serializable;

/**
 * RolePlayingExceptionMessages holds the exception messages to be use in the app.
 * 
 * @author Ali Gelenler
 *
 */
public class RolePlayingExceptionMessages implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1012844378326770431L;
	private String errorCode;
	private String errorMessage;

	private RolePlayingExceptionMessages(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public static final RolePlayingExceptionMessages roleplaying_0000 = new RolePlayingExceptionMessages("0000", "An error occurred try again!!");
	public static final RolePlayingExceptionMessages roleplaying_0001 = new RolePlayingExceptionMessages("0001", "Encode password error!");
	public static final RolePlayingExceptionMessages roleplaying_0002 = new RolePlayingExceptionMessages("0002", "File read/write error!");
	public static final RolePlayingExceptionMessages roleplaying_0003 = new RolePlayingExceptionMessages("0003", "Game list fetch error!");
	public static final RolePlayingExceptionMessages roleplaying_0004 = new RolePlayingExceptionMessages("0004", "All the fields are required! Please provide the requred fields");
	public static final RolePlayingExceptionMessages roleplaying_0005 = new RolePlayingExceptionMessages("0005", "The same username exits in the system! Please provide a different one");

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
