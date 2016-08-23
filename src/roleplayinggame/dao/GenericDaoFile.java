package roleplayinggame.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roleplayinggame.constant.Constants;
import roleplayinggame.exception.RolePlayingException;
import roleplayinggame.exception.RolePlayingExceptionMessages;
import roleplayinggame.model.ClassHolder;
import roleplayinggame.model.FieldHolder;
import roleplayinggame.util.FileUtil;
import roleplayinggame.util.ReflectionUtil;
import roleplayinggame.util.StringUtil;
import roleplayinggame.visitor.ConcreteVisitor;
import roleplayinggame.visitor.ReflectiveVisitor;

/**
 * The implementation of GenericIDaoFile which holds the generic methods that can be used from any of the particular Dao class.
 * 
 * @author Ali Gelenler
 *
 */
class GenericDaoFile<T> implements GenericIDaoFile<T> {

	public static final Logger logger = LoggerFactory.getLogger(GenericDaoFile.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see roleplayinggame.dao.GenericIDaoFile#readFileIntoList(java.lang.Object, java.lang.String)
	 */
	@Override
	public List<T> readFileIntoList(Class<T> t, String filename) throws RolePlayingException {
		List<T> list = new ArrayList<>();
		try {
			try (Stream<String> stream = Files.lines(Paths.get(filename))) {
				List<String> tmpList = stream.collect(Collectors.toList());
				String[] headers = tmpList.get(0).split(Constants.FILE_DELIMITER);
				for (int i = 0; i < headers.length; i++) {
					if (headers[i].contains(Constants.UNDERSCORE)) {
						headers[i] = StringUtil.makeTheStringLowerCamelCase(headers[i], Constants.UNDERSCORE);
					} else
						headers[i] = headers[i].toLowerCase(Constants.DEFAULT_LOCALE);
				}
				tmpList.remove(0);
				Field[] fields = t.getDeclaredFields();
				String[] data = null;
				Class<?> klazz = Class.forName(t.getName());
				for (String elem : tmpList) {
					if (!StringUtil.isEmpty(elem)) {
						data = elem.split(Constants.FILE_DELIMITER);
						Class<?>[] constructorTypes = new Class[fields.length];
						Object[] constructorVals = new Object[fields.length];
						int i = 0, k = 0;
						for (int j = 0; j < data.length; j++) {
							for (Field field : fields) {
								if (field.getType().isPrimitive() || field.getType().equals(String.class)) {
									if (headers[j].equalsIgnoreCase(field.getName())) {
										constructorTypes[i++] = field.getType();
										constructorVals[k++] = ReflectionUtil.getObjectFromType(data[j], field.getType());
										break;
									}
								} else {
									StringBuilder isFound = new StringBuilder();
									FieldHolder foundField = new FieldHolder();
									FileUtil.compareInnerFields(field, headers[j], isFound, foundField);
									if (Constants.OK.equals(isFound.toString())) {
										Class<?> tmpKlazz = Class.forName(field.getType().getName());
										Object processedType = null;
										int cnt = 0;
										for (Class<?> cls : constructorTypes) {
											if (cls != null && cls.equals(tmpKlazz)) {
												processedType = constructorVals[cnt];
												break;
											}
											cnt++;
										}
										if (processedType != null) {
											Method mGet = processedType.getClass().getDeclaredMethod(Constants.GET + foundField.getField().getDeclaringClass().getSimpleName(), null);
											Object tmpObj = mGet.invoke(processedType, null);
											boolean setToMain = false;
											if (tmpObj == null) {
												tmpObj = foundField.getField().getDeclaringClass().getConstructor().newInstance();
												setToMain = true;
											}
											Field f1 = tmpObj.getClass().getDeclaredField(headers[j]);
											Method mSet = tmpObj.getClass().getDeclaredMethod(Constants.SET + StringUtil.getOnlyFirstCharUpper(headers[j]), f1.getType());
											mSet.invoke(tmpObj, ReflectionUtil.getObjectFromType(data[j], f1.getType()));
											if (setToMain) {
												mSet = processedType.getClass().getDeclaredMethod(Constants.SET + foundField.getField().getDeclaringClass().getSimpleName(), tmpObj.getClass());
												mSet.invoke(processedType, tmpObj);
											}
										} else {
											Class<?> type = field.getType().getDeclaredField(headers[j]).getType();
											ClassHolder holder = new ClassHolder(type);
											ReflectiveVisitor visitor = new ConcreteVisitor();
											Object tmpObj = ReflectionUtil.getDataFromType(tmpKlazz, holder, data[j], visitor);
											constructorTypes[i++] = field.getType();
											constructorVals[k++] = tmpObj;
										}
										break;
									}
								}
							}
						}
						list.add((T) klazz.getConstructor(constructorTypes).newInstance(constructorVals));
					}
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0002);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0002);
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), e);
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0002);
		} catch (SecurityException e) {
			logger.error(e.getMessage(), e);
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0002);
		} catch (InstantiationException e) {
			logger.error(e.getMessage(), e);
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0002);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0002);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(), e);
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0002);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(), e);
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0002);
		} catch (NoSuchFieldException e) {
			logger.error(e.getMessage(), e);
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0002);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see roleplayinggame.dao.GenericIDaoFile#writeDataIntoFile(java.lang.Object, java.lang.String, boolean)
	 */
	@Override
	public boolean writeDataIntoFile(T t, String filename, boolean append) throws RolePlayingException {
		try (FileWriter fileWriter = new FileWriter(filename, append); BufferedWriter bufWriter = new BufferedWriter(fileWriter); PrintWriter out = new PrintWriter(bufWriter)) {
			out.println(t.toString());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0002);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see roleplayinggame.dao.GenericIDaoFile#writeTextIntoFile(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public boolean writeTextIntoFile(String s, String filename, boolean append) throws RolePlayingException {
		try (FileWriter fileWriter = new FileWriter(filename, append); BufferedWriter bufWriter = new BufferedWriter(fileWriter); PrintWriter out = new PrintWriter(bufWriter)) {
			out.println(s);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RolePlayingException(RolePlayingExceptionMessages.roleplaying_0002);
		}
		return true;
	}

}
