package ua.com.csltd.services;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by valeriy_solyanik
 * on 02.10.2015.
 */
public class Test {

	static void test(){
		Class cl = null;
		cl.getMethods();
	}

	public static void main(String[] args) throws Exception {
		/*try {
			test();

*//*
			File file = new File("D:\\response.xml");
			//JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement customer = (JAXBElement) jaxbUnmarshaller.unmarshal(file);
			*//**//*CreateBillsResponse response = (CreateBillsResponse)customer.getValue();
			System.out.println(response.getBill().get(0));*//*

		} catch (JAXBException e) {
			e.printStackTrace();
		}*/
	}
}
