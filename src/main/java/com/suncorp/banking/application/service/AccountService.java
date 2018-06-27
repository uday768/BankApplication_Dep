package com.suncorp.banking.application.service;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suncorp.banking.application.repo.AccountRepository;
import com.suncorp.banking.application.vo.AccountVo;

@Service
public class AccountService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountRepository accountRepo; 
	
	public void createAccount(AccountVo accountDetails){
		logger.info("Creating the account for the user:"+accountDetails.getFirstname());
		Timestamp timestamp = new Timestamp(new Date().getTime());
		String accountNumberFeed = accountDetails.getFirstname() + accountDetails.getLastname() + timestamp.toString();
		SecureRandom random = new SecureRandom(accountNumberFeed.getBytes());
		accountDetails.setAccountNumber(Integer.toString(1000000000 + random.nextInt(90000000)));
		accountRepo.save(accountDetails);
	}
}
