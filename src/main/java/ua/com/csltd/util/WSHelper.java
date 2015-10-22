package ua.com.csltd.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * Created by valeriy_solyanik
 * on 21.10.2015.
 */
public class WSHelper {
	<T extends Object> T getDataForLog(Object object) {
		Class<?> tClass = object.getClass();
		Field[] fields = tClass.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
		}
		BigDecimal decimal = (BigDecimal) null;

		return null;
	}
}
