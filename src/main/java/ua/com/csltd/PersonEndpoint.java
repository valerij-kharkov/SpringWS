package ua.com.csltd;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import ua.com.csltd.beans.GetPersonsRequest;
import ua.com.csltd.beans.Person;
import ua.com.csltd.beans.PersonResponse;

/**
 * Created by valeriy_solyanik
 * on 07.09.2015.
 */
@Endpoint
public class PersonEndpoint implements MarshallingPersonService {

	/**
	 * Gets person list.
	 */
	@PayloadRoot(localPart=GET_PERSONS_REQUEST, namespace=NAMESPACE)
	public PersonResponse getPersons(GetPersonsRequest request) {
		PersonResponse response =  new PersonResponse();
		response.getPerson().add(
				new Person() {{
					setId(1);
					setFirstName("Joe");
				setLastName("Smith");}});
		response.getPerson().add(
				new Person() {{
					setId(2);
					setFirstName("Joe2");
					setLastName("Smith2");}});
		return response;

	}

}
