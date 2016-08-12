package ua.com.csltd.util;

import oracle.sql.ARRAY;
import oracle.sql.STRUCT;
import org.springframework.data.jdbc.support.oracle.StructMapper;

import java.beans.PropertyEditorSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by valeriy_solyanik
 * on 09.08.2016.
 */
public class ArrayTypeEditor<T> extends PropertyEditorSupport {

	private Class<T> clazz;
	private StructMapper mapper;

	public ArrayTypeEditor(StructMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);
	}

	@Override
	public Object getValue() {
		List<T> objectList = new ArrayList<>();

		try {
			ARRAY array = (ARRAY) super.getValue();

			Object[] dataList = (Object[]) array.getArray();

			for (Object data : dataList) {
				STRUCT struct = (STRUCT) data;
				objectList.add((T) mapper.fromStruct(struct));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return objectList;
	}
}
