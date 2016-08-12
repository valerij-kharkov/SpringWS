package ua.com.csltd.util;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import org.springframework.data.jdbc.support.oracle.StructMapper;
import org.springframework.jdbc.core.support.AbstractSqlTypeValue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by valeriy_solyanik
 * on 10.08.2016.
 */
public class SqlTypeArrayValue<T> extends AbstractSqlTypeValue {

	private List<T> objectList;
	private String structTypeName;
	private StructMapper structMapper;

	public SqlTypeArrayValue(List<T> objectList, String structTypeName, StructMapper structMapper) {
		this.objectList = objectList;
		this.structTypeName = structTypeName;
		this.structMapper = structMapper;
	}

	@Override
	protected Object createTypeValue(Connection con, int sqlType, String typeName) throws SQLException {
		ArrayDescriptor arrayDescriptor = new ArrayDescriptor(typeName, con);
		List<STRUCT> dataList = new ArrayList<>();
		if (objectList != null) {
			for (Object o : objectList) {
				dataList.add(structMapper.toStruct(o, con, structTypeName));
			}
		}
		return new ARRAY(arrayDescriptor, con, dataList.toArray());
	}
}