/**
 * Created by valeriy_solyanik
 * on 11.09.2015.
 */

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.client.core.WebServiceTemplate;
import ua.com.csltd.beans.Person;
import ua.com.csltd.beans.PersonRequest;
import ua.com.csltd.beans.PersonResponse;
import ua.com.csltd.dao.PersonDAO;
import ua.com.csltd.services.AsyncService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-ws-contextTest.xml"})
public class Test {
	@Autowired
	WebServiceTemplate personWsTemplate;
	@Autowired
	AsyncService asyncService;
	@Autowired
	PersonDAO personDAO;

	@org.junit.Test
	public void getResponse(){
		PersonRequest request = new PersonRequest();
		request.setName("111");
		PersonResponse response = (PersonResponse)personWsTemplate.marshalSendAndReceive(request);
		Assert.assertNotNull(response);
	}

	@org.junit.Test
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void getSleep(){
		/*List<Future<String>> futureList = new ArrayList<>();
		for (SearchAccountDealInfo account : accountResInfoList) {
			futureList.add(otpFlexDealInfoAsyncService.getDepDetOnSeparateThread(account.getFlexAccountNo(), account.getBranchNo(), contragentTypeId));
		}
*/
		List<Person> personList = personDAO.getPersons();
		Assert.assertNotNull(personList);

		/*PersonRequest request = new PersonRequest();
		request.setName("111");
		PersonResponse response = (PersonResponse)personWsTemplate.marshalSendAndReceive(request);
		Assert.assertNotNull(response);*/
	}
}
