package roleplayinggame.dao;

import java.util.List;

import roleplayinggame.exception.RolePlayingException;

/**
 * GenericIDaoFile holds the generic methods that can be used from any of the particular Dao class.
 * 
 * @author Ali Gelenler
 *
 */
public interface GenericIDaoFile<T> {

	/**
	 * This generic method is used to read a file content into a List of given type T using reflection
	 * 
	 * @param t
	 * @param filename
	 * @return List<T>
	 * @throws RolePlayingException
	 */
	public List<T> readFileIntoList(Class<T> t, String filename) throws RolePlayingException;

	/**
	 * Writes the string representation of the given type T to a given file using an optional append parameter.
	 * 
	 * @param t
	 * @param filename
	 * @param append
	 * @return boolean
	 * @throws RolePlayingException
	 */
	public boolean writeDataIntoFile(T t, String filename, boolean append) throws RolePlayingException;

	/**
	 * Writes a string to a given file using an optional append parameter.
	 * 
	 * @param s
	 * @param filename
	 * @param append
	 * @return boolean
	 * @throws RolePlayingException
	 */
	public boolean writeTextIntoFile(String s, String filename, boolean append) throws RolePlayingException;

}
