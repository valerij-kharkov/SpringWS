package ua.com.csltd.util;

import java.lang.reflect.Field;

/**
 * Created by valeriy_solyanik
 * on 21.10.2015.
 */
public class WSHelper {
 <T extends  Object> T getDataForLog (Object object){
	 Class<?> tClass =  object.getClass();
	 Field [] fields =  tClass.getDeclaredFields();
	// fields.
	 return null;
 }
}
