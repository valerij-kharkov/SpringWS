
import oracle.jdbc.OracleTypes;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.client.core.WebServiceTemplate;
import ua.com.csltd.beans.AccountCSInfoFromB2Bean;
import ua.com.csltd.beans.Person;
import ua.com.csltd.beans.PersonRequest;
import ua.com.csltd.beans.PersonResponse;
import ua.com.csltd.dao.PersonDAO;
import ua.com.csltd.services.AsyncService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-ws-contextTest.xml"
		/*"classpath*:spring-ws-context.xml"*/})
public class Test {
	@Autowired
	WebServiceTemplate personWsTemplateTest;
	@Autowired
	AsyncService asyncService;
	@Autowired(required = false)
	PersonDAO personDAO;

	@Autowired
	JdbcTemplate b2jdbcTemplate;
	@Autowired
	SimpleJdbcCall simpleJdbcCall;

	@org.junit.Test
	public void getResponse() {
		PersonRequest request = new PersonRequest();
		PersonResponse response = (PersonResponse) personWsTemplateTest.marshalSendAndReceive(request);
		Assert.assertNotNull(response);
	}

	@org.junit.Test
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void asyncService() throws ExecutionException, InterruptedException {
		List<Person> personList = personDAO.getPersons();
		List<Future<Person>> futureList = new ArrayList<>();

		for (Person person : personList) {
			futureList.add(asyncService.getDepDetOnSeparateThread(person));
		}
		for (Future<Person> personFuture : futureList) {
			personList.add(personFuture.get());
		}
		Assert.assertNotNull(personList);
	}


	@org.junit.Test
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void getData() throws ExecutionException, InterruptedException {

		List<AccountCSInfoFromB2Bean> accountCSInfoFromB2BeanList =
				simpleJdbcCall
				.withCatalogName("PKG_IFOBSGATE")
				.withFunctionName("GetAccountInfo")
				.declareParameters(
						new SqlOutParameter("return", OracleTypes.CURSOR, new BeanPropertyRowMapper<>(AccountCSInfoFromB2Bean.class)),
						new SqlParameter("in_accountno", OracleTypes.VARCHAR),
						new SqlParameter("in_currencyid", OracleTypes.NUMBER),
						new SqlParameter("in_siteid", OracleTypes.NUMBER)
				).executeFunction(List.class,
						"26050018875934",
						980,
						300528
				);

		Assert.assertNotNull(accountCSInfoFromB2BeanList);
	}
}
