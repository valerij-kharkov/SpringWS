package ua.com.csltd.util;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

/**
 * Created by valeriy_solyanik
 * on 22.10.2015.
 */
@SoapFault(faultCode = FaultCode.CUSTOM)
public class WSException extends RuntimeException {
	public WSException(String message){
		super(message);
	}
}
