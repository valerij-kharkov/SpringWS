package ua.com.csltd.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.com.csltd.beans.*;
import ua.com.csltd.services.MarshallingPersonService;

/**
 * Created by valeriy_solyanik
 * on 07.09.2015.
 */
@Endpoint
public class PersonEndpoint {
	public final static String NAMESPACE = "http://csltd.com.ua/personService/person";

	@Autowired
	MarshallingPersonService service;

	@PayloadRoot(localPart = "PersonRequest", namespace = NAMESPACE)
	@ResponsePayload
	public PersonResponse getPersons(@RequestPayload PersonRequest request) {
		return service.getPersons(request);
	}

	@PayloadRoot(localPart = "AddPersonRequest", namespace = NAMESPACE)
	@ResponsePayload
	public AddPersonResponse addPerson(@RequestPayload AddPersonRequest request) {
		return service.addPerson(request);
	}

	@PayloadRoot(localPart = "ChangePersonRequest", namespace = NAMESPACE)
	@ResponsePayload
	public ChangePersonResponse changePerson(@RequestPayload ChangePersonRequest request) {
		return service.changePerson(request);
	}
}
