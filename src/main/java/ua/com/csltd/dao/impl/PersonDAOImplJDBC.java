package ua.com.csltd.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.csltd.beans.Person;
import ua.com.csltd.dao.PersonDAO;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		jdbcTemplate.update("INSERT INTO PERSON VALUES(?, ?, ?)", person.getId(), person.getFirstName(), person.getLastName());
	}

	@Override
	public void changePerson(Person person) {
		jdbcTemplate.update("UPDATE PERSON set first_name = ?, last_name = ? where id = ?",  person.getFirstName(), person.getLastName(), person.getId());
	}

	@Override
	public List<Person> getPersons() {
		return jdbcTemplate.query("select * from person", new RowMapper() {
			@Override
			public Person mapRow(ResultSet resultSet, int i) throws SQLException {
				Person person = new Person();
				person.setId(BigInteger.valueOf(resultSet.getInt("id")));
				person.setLastName(resultSet.getString("last_name"));
				person.setFirstName(resultSet.getString("first_name "));
				return person;
			}
		});
	}
}
