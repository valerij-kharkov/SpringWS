package ua.com.csltd.util;

/**
 * Created by valeriy_solyanik
 * on 09.08.2016.
 */

import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.*;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.jdbc.support.oracle.StructMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: makiyov
 * Date: 28.04.2015
 * Time: 11:51
 */
public class BeanPropertyArrayStructMapper implements StructMapper {

	private static final Logger logger = LoggerFactory.getLogger(BeanPropertyArrayStructMapper.class);

	/**
	 * The class we are mapping to
	 */
	protected Class<?> mappedClass;

	/**
	 * Map of the fields we provide mapping for
	 */
	private Map<String, PropertyDescriptor> mappedFields;

	public BeanPropertyArrayStructMapper() {
	}

	public BeanPropertyArrayStructMapper(Class<?> mappedClass) {
		initialize(mappedClass);
	}

	protected void initBeanWrapper(BeanWrapper bw) {
		registerCustomEditor(bw);
	}

	public void registerCustomEditor(BeanWrapper bw) {}

	/**
	 * Initialize the mapping metadata for the given class.
	 *
	 * @param mappedClass the mapped class.
	 */
	protected void initialize(Class<?> mappedClass) {
		this.mappedClass = mappedClass;
		this.mappedFields = new HashMap<>();
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(mappedClass);
		for (PropertyDescriptor pd : pds) {
			if (pd.getWriteMethod() != null || pd.getReadMethod() != null) {
				this.mappedFields.put(pd.getName().toLowerCase(), pd);
				String underscoredName = underscoreName(pd.getName());
				if (!pd.getName().toLowerCase().equals(underscoredName)) {
					this.mappedFields.put(underscoredName, pd);
				}
			}
		}
	}

	/**
	 * Convert a name in camelCase to an underscored name in lower case.
	 * Any upper case letters are converted to lower case with a preceding underscore.
	 *
	 * @param name the string containing original name
	 * @return the converted name
	 */
	private String underscoreName(String name) {
		StringBuilder result = new StringBuilder();
		if (name != null && name.length() > 0) {
			result.append(name.substring(0, 1).toLowerCase());
			for (int i = 1; i < name.length(); i++) {
				String s = name.substring(i, i + 1);
				if (s.equals(s.toUpperCase())) {
					result.append("_");
					result.append(s.toLowerCase());
				} else {
					result.append(s);
				}
			}
		}
		return result.toString();
	}

	/**
	 * Get the class that we are mapping to.
	 */
	public final Class<?> getMappedClass() {
		return this.mappedClass;
	}

	/**
	 * Set the class that each row should be mapped to.
	 */
	public void setMappedClass(Class<?> mappedClass) {
		if (this.mappedClass == null) {
			initialize(mappedClass);
		} else {
			if (!this.mappedClass.equals(mappedClass)) {
				throw new InvalidDataAccessApiUsageException("The mapped class can not be reassigned to map to " +
						mappedClass + " since it is already providing mapping for " + this.mappedClass);
			}
		}
	}

	/**
	 * Get the fields that we are mapping to.
	 */
	public final Map<String, PropertyDescriptor> getMappedFields() {
		return this.mappedFields;
	}


	public STRUCT toStruct(Object object, Connection conn, String typeName) throws SQLException {
		StructDescriptor descriptor = new StructDescriptor(typeName, conn);
		ResultSetMetaData rsmd = descriptor.getMetaData();
		int columns = rsmd.getColumnCount();
		Object[] values = new Object[columns];
		for (int i = 1; i <= columns; i++) {
			String column = JdbcUtils.lookupColumnName(rsmd, i).toLowerCase();
			PropertyDescriptor fieldMeta = (PropertyDescriptor) this.mappedFields.get(column);
			if (fieldMeta != null) {
				BeanWrapper bw = new BeanWrapperImpl(object);
				if (bw.isReadableProperty(fieldMeta.getName())) {
					try {
						if (logger.isDebugEnabled()) {
							logger.debug("Mapping column named \"" + column + "\"" +
									" to property \"" + fieldMeta.getName() + "\"");
						}
						values[i - 1] = bw.getPropertyValue(fieldMeta.getName());
					} catch (NotReadablePropertyException ex) {
						throw new DataRetrievalFailureException(
								"Unable to map column " + column + " to property " + fieldMeta.getName(), ex);
					}
				} else {
					logger.warn("Unable to access the getter for " + fieldMeta.getName() +
							".  Check that " + "get" + StringUtils.capitalize(fieldMeta.getName()) +
							" is declared and has public access.");
				}
			}
		}
		STRUCT struct = new STRUCT(descriptor, conn, values);
		return struct;
	}

	/**
	 * Extract the values for all attributes in the struct.
	 * <p>Utilizes public setters and result set metadata.
	 *
	 * @see java.sql.ResultSetMetaData
	 */
	public Object fromStruct(STRUCT struct) throws SQLException {
		Assert.state(this.mappedClass != null, "Mapped class was not specified");
		Object mappedObject = BeanUtils.instantiateClass(this.mappedClass);
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);
		initBeanWrapper(bw);

		ResultSetMetaData rsmd = struct.getDescriptor().getMetaData();
		Object[] attr = struct.getAttributes();
		int columnCount = rsmd.getColumnCount();
		for (int index = 1; index <= columnCount; index++) {
			String column = JdbcUtils.lookupColumnName(rsmd, index).toLowerCase();
			PropertyDescriptor pd = (PropertyDescriptor) this.mappedFields.get(column);
			if (pd != null) {
				Object value = attr[index - 1];
				if (logger.isDebugEnabled()) {
					logger.debug("Mapping column '" + column + "' to property '" +
							pd.getName() + "' of type " + pd.getPropertyType());
				}
				if (bw.isWritableProperty(pd.getName())) {
					try {
						bw.setPropertyValue(pd.getName(), value);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					// Если нету сеттера для проперти не нужно кидать ошибку!
					logger.warn("Unable to access the setter for " + pd.getName() +
							".  Check that " + "set" + StringUtils.capitalize(pd.getName()) +
							" is declared and has public access.");
				}
			}
		}

		return mappedObject;
	}
}
