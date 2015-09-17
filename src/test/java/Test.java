/**
 * Created by valeriy_solyanik
 * on 11.09.2015.
 */

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;
import ua.com.csltd.beans.PersonRequest;
import ua.com.csltd.beans.PersonResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-ws-contextTest.xml"})
public class Test {
	@Autowired
	WebServiceTemplate personWsTemplate;
	@org.junit.Test
	public void getResponse(){
		PersonRequest request = new PersonRequest();
		request.setName("111");
		PersonResponse response = (PersonResponse)personWsTemplate.marshalSendAndReceive(request);
		Assert.assertNotNull(response);
	}

	@org.junit.Test
	public void getSleep(){
		/*PersonRequest request = new PersonRequest();
		request.setName("111");
		PersonResponse response = (PersonResponse)personWsTemplate.marshalSendAndReceive(request);
		Assert.assertNotNull(response);*/
	}
}
