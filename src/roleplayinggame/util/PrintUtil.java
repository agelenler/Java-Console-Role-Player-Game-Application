package roleplayinggame.util;

/**
 * Print related utility operations
 * 
 * @author Ali Gelenler
 *
 */
public abstract class PrintUtil {

	/**
	 * Go to a new line
	 */
	public static final void println() {
		System.out.println("");
	}

	/**
	 * Print the input in a new line
	 * 
	 * @param input
	 */
	public static final void println(String input) {
		System.out.println(input);
	}

	/**
	 * Print the input
	 * 
	 * @param input
	 */
	public static final void print(String input) {
		System.out.print(input);
	}
}
