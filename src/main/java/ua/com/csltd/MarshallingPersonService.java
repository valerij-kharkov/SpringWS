package ua.com.csltd;

import ua.com.csltd.beans.GetPersonsRequest;
import ua.com.csltd.beans.PersonResponse;

/**
 * Created by valeriy_solyanik
 * on 07.09.2015.
 */
public interface MarshallingPersonService {
	public final static String NAMESPACE = "http://csltd.com.ua/beans";
	public final static String GET_PERSONS_REQUEST = "get-persons-request";

	/**
	 * Gets person list.
	 */
	public PersonResponse getPersons(GetPersonsRequest request);
}
