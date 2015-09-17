package ua.com.csltd.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.csltd.beans.Person;
import ua.com.csltd.dao.PersonDAO;

import java.util.List;

/**
 * Created by valeriy_solyanik
 * on 14.09.2015.
 */
@Repository
public class PersonDAOImplJDBC implements PersonDAO{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void addPerson(Person person) {

	}

	@Override
	public void changePerson(Person person) {

	}

	@Override
	public List<Person> getPersons() {
		return null;
	}
}
