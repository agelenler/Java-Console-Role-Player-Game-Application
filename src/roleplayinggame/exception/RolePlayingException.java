package roleplayinggame.exception;

import java.text.MessageFormat;

/**
 * RolePlayingException is the customized userd defined exception class to be used in exceptional situation. It holds an additional errorCode which is not currently available in java.lang.Exception classs.
 * 
 * @author Ali Gelenler
 *
 */
public class RolePlayingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1585685527824138110L;
	private String errorCode;

	public String getHataKodu() {
		return errorCode;
	}

	public RolePlayingException() {
		// TODO Auto-generated constructor stub
	}

	public RolePlayingException(String errorCode, String hataMesaji) {
		super(hataMesaji);
		this.errorCode = errorCode;
	}

	/**
	 * Construct a RolePlayingException using a RolePlayingExceptionMessages object
	 * 
	 * @param message
	 */
	public RolePlayingException(RolePlayingExceptionMessages message) {
		super(message.getErrorMessage());
		this.errorCode = message.getErrorCode();
	}

	/**
	 * Construct a RolePlayingException using a RolePlayingExceptionMessages object and parameters
	 * 
	 * @param message
	 */
	public RolePlayingException(RolePlayingExceptionMessages message, Object[] params) {
		super(formatMessage(message.getErrorMessage(), params));
		this.errorCode = message.getErrorCode();
	}

	/**
	 * Construct a RolePlayingException from a Java Exception
	 * 
	 * @param message
	 */
	public RolePlayingException(Exception e) {
		super(e.getMessage());
		this.errorCode = "0001";
	}

	/**
	 * Construct a formatted message from a strign and parameters
	 * 
	 * @param message
	 * @param params
	 * @return String
	 */
	private static String formatMessage(String message, Object[] params) {
		MessageFormat mf = new MessageFormat(message);
		return mf.format(params);
	}
}
