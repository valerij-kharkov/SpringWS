package ua.com.csltd;

import ua.com.csltd.beans.PersonRequest;
import ua.com.csltd.beans.PersonResponse;

/**
 * Created by valeriy_solyanik
 * on 07.09.2015.
 */
public interface MarshallingPersonService {
	public final static String NAMESPACE = "http://csltd.com.ua/personService/person";

	/**
	 * Gets person list.
	 */
	public PersonResponse getPersons(PersonRequest request);
}
