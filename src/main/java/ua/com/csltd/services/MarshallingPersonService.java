package ua.com.csltd.services;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.csltd.beans.*;
import ua.com.csltd.dao.PersonDAO;

import java.math.BigInteger;

/**
 * Created by valeriy_solyanik
 * on 07.09.2015.
 */
@Service
public class MarshallingPersonService {
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(MarshallingPersonService.class);

	@Autowired
	PersonDAO personDAO;

	/**
	 * Gets person list.
	 */
	public PersonResponse getPersons(PersonRequest request){
		logger.debug("Request : {}", request);
		PersonResponse response = new PersonResponse();
		response.getPerson().addAll(personDAO.getPersons());
		logger.debug("Response : {}", response);
		return response;
	}

	public AddPersonResponse addPerson(AddPersonRequest request){
		logger.debug("Request : {}", request);
		AddPersonResponse response = new AddPersonResponse();
		personDAO.addPerson(request.getFirstName(), request.getLastName());
		response.setResult("Ok");
		logger.debug("Response : {}", response);
		return response;
	}

	public ChangePersonResponse changePerson(ChangePersonRequest request){
		logger.debug("Request : {}", request);
		ChangePersonResponse response = new ChangePersonResponse();
		personDAO.changePerson(request.getPerson());
		response.setResult("Ok");
		logger.debug("Response : {}", response);
		return response;
	}



   @Scheduled(fixedDelay=1000000)
	public void sleep()  {
		logger.debug("Request : {}", Thread.currentThread().getName());
		try {
			Thread.sleep(50000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("1");
	}
    @Scheduled(fixedRate=1000000)
	public void sleep2()  {
		logger.debug("Request2 : {}, threadName : {}", System.currentTimeMillis(), Thread.currentThread().getName());
		try {
			Thread.sleep(60000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("2");
	}
}
