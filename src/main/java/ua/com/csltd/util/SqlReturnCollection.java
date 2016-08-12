package ua.com.csltd.util;

import oracle.sql.ARRAY;
import oracle.sql.STRUCT;
import org.springframework.beans.BeanWrapper;
import org.springframework.data.jdbc.support.oracle.StructMapper;
import org.springframework.jdbc.core.SqlReturnType;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by valeriy_solyanik
 * on 09.08.2016.
 */
public abstract class SqlReturnCollection<T> implements SqlReturnType {

	/**
	 * The object that will do the mapping
	 **/
	private StructMapper mapper;

	private Class<T> clazz;

	/**
	 * Constructor that takes one parameter with the class that the retrieved data should be
	 * mapped into.
	 *
	 * @param targetClass JavaBean class that STRUCT attributes will be mapped into
	 */
	public SqlReturnCollection(Class<T> targetClass) {
		this.mapper = new BeanPropertyArrayStructMapper(targetClass) {
			@Override
			public void registerCustomEditor(BeanWrapper bw) {
				registryEditor(bw);
			}
		};
		this.clazz = targetClass;
	}

	@Override
	public List<T> getTypeValue(CallableStatement cs, int paramIndex, int sqlType, String typeName) throws SQLException {
		List<T> objectList = new ArrayList<>();

		ARRAY array = (ARRAY) cs.getObject(paramIndex);
		Object[] dataList = (Object[]) array.getArray();

		for (Object data : dataList) {
			STRUCT struct = (STRUCT) data;
			objectList.add((T) mapper.fromStruct(struct));

		}

		return objectList;
	}

	protected void registryEditor(BeanWrapper bw) {

	}

}