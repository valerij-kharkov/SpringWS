package ua.com.csltd;

import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.com.csltd.beans.Person;
import ua.com.csltd.beans.PersonRequest;
import ua.com.csltd.beans.PersonResponse;

import java.math.BigInteger;

/**
 * Created by valeriy_solyanik
 * on 07.09.2015.
 */
@Endpoint
public class PersonEndpoint implements MarshallingPersonService {
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(PersonEndpoint.class);

	/**
	 * Gets person list.
	 */
	@PayloadRoot(localPart="PersonRequest", namespace = NAMESPACE)
	@ResponsePayload
	public PersonResponse getPersons(@RequestPayload PersonRequest request) {
		logger.debug("Request : {}", request);
		PersonResponse response =  new PersonResponse();
		response.getPerson().add(
				new Person() {{
					setId(BigInteger.ONE);
					setFirstName("Joe");
				setLastName("Smith");}});
		response.getPerson().add(
				new Person() {{
					setId(BigInteger.TEN);
					setFirstName("Joe2");
					setLastName("Smith2");}});
		logger.debug("Response : {}", response);
		return response;

	}

}
