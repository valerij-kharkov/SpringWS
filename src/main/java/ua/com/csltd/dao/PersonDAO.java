package ua.com.csltd.dao;

import ua.com.csltd.beans.Person;

import java.util.List;

/**
 * Created by valeriy_solyanik
 * on 14.09.2015.
 */
public interface PersonDAO {
	void addPerson(String firstName, String lastname);

	void changePerson(Person person);

	List<Person> getPersons();
}
