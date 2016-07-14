package ua.com.csltd.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.csltd.beans.Person;
import ua.com.csltd.dao.PersonDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by valeriy_solyanik
 * on 14.09.2015.
 */
@Repository
public class PersonDAOImplJDBC implements PersonDAO {
	private static final Logger log = LoggerFactory.getLogger(PersonDAOImplJDBC.class);
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void addPerson(String firstName, String lastname) {
		jdbcTemplate.update("INSERT INTO PERSON VALUES(SEQ_ID.NEXTVAL, ?, ?)", firstName, lastname);
	}

	@Override
	public void changePerson(Person person) {
		jdbcTemplate.update("UPDATE PERSON set first_name = ?, last_name = ? where id = ?", person.getFirstName(), person.getLastName(), person.getId());
	}

	@Override
	public List<Person> getPersons() {
		return jdbcTemplate.query("select * from person", new RowMapper() {
			@Override
			public Person mapRow(ResultSet resultSet, int i) throws SQLException {
				Person person = new Person();
				person.setId(resultSet.getLong("ID"));
				person.setLastName(resultSet.getString("LAST_NAME"));
				person.setFirstName(resultSet.getString("FIRST_NAME"));
				return person;
			}
		});
	}

	@Override
	public Person modifyPerson(Person person) {
		log.debug(Thread.currentThread().getName() + " Start");
		Person newPerson = new Person();
		newPerson.setId(person.getId() * 15000000);
		newPerson.setFirstName(person.getFirstName() + "New");
		newPerson.setLastName(person.getLastName() + "New");
		log.debug(Thread.currentThread().getName() + " End");
		return newPerson;
	}
}
