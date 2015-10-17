package ua.com.csltd.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by valeriy_solyanik
 * on 17.10.2015.
 */
@Service
public class AsyncService {
	@Async
	public Future<String> getDepDetOnSeparateThread(String flexAccountNo, String branch, String contragentTypeId) {
		return null;//new AsyncResult<>(otpFlexDealInfoDAO.getDetailDepositInfoByAccount(flexAccountNo, branch, contragentTypeId));
	}
}
