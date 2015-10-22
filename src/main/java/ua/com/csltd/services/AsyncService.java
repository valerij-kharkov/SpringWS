package ua.com.csltd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import ua.com.csltd.beans.Person;
import ua.com.csltd.dao.PersonDAO;

import java.util.concurrent.Future;

/**
 * Created by valeriy_solyanik
 * on 17.10.2015.
 */
@Service
public class AsyncService {
	@Autowired
	PersonDAO personDAO;

	@Async("executor")
	public Future<Person> getDepDetOnSeparateThread(Person person) {
		return new AsyncResult<>(personDAO.modifyPerson(person));
	}
}
