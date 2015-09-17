package ua.com.csltd.services;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.csltd.beans.Person;
import ua.com.csltd.beans.PersonRequest;
import ua.com.csltd.beans.PersonResponse;

import java.math.BigInteger;

/**
 * Created by valeriy_solyanik
 * on 07.09.2015.
 */
@Service
public class MarshallingPersonService {
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(MarshallingPersonService.class);

	/**
	 * Gets person list.
	 */
	public PersonResponse getPersons(PersonRequest request){
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
    @Scheduled(fixedDelay=1000)
	public void sleep()  {
		logger.debug("Request : {}", System.currentTimeMillis());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("1");
	}
    @Scheduled(fixedRate=1000)
	public void sleep2()  {
		logger.debug("Request2 : {}", System.currentTimeMillis());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("2");
	}
}
