package ua.com.csltd.util;

import com.ibm.icu.text.Transliterator;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Enumeration;

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

	public static void main(String[] args) {
		String bgString = " Єнакієве Гаєвич згорани";
	/*	Enumeration<String> e = Transliterator.getAvailableIDs();
		while(e.hasMoreElements()){
			System.out.println(e.nextElement());
		}*/

		String UKRAINIAN_TO_LATIN = "Ukrainian-Latin/BGN";

		Transliterator ukrainianToLatin = Transliterator.getInstance(UKRAINIAN_TO_LATIN);
		String result1 = ukrainianToLatin.transliterate(bgString);
		System.out.println("Ukrainian to Latin:" + result1);
	}

}
