package com.suncorp.banking.application.service;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suncorp.banking.application.exceptions.AccountNotExistsException;
import com.suncorp.banking.application.repo.AccountRepository;
import com.suncorp.banking.application.repo.AccountTransactionRepository;
import com.suncorp.banking.application.vo.AccountTransactionVO;
import com.suncorp.banking.application.vo.AccountVo;

@Service
public class AccountService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AccountRepository accountRepo; 
	
	@Autowired
	AccountTransactionRepository transRepo;
	
	public void createAccount(AccountVo accountDetails){
		logger.info("Creating the account for the user:"+accountDetails.getFirstname());
		Timestamp timestamp = new Timestamp(new Date().getTime());
		String accountNumberFeed = accountDetails.getFirstname() + accountDetails.getLastname() + timestamp.toString();
		SecureRandom random = new SecureRandom(accountNumberFeed.getBytes());
		accountDetails.setAccountNumber(Integer.toString(1000000000 + random.nextInt(90000000)));
		accountRepo.save(accountDetails);
	}
	
	
	
	@Transactional
	// Need to add the accou not found exception
	//https://www.callicoder.com/spring-boot-rest-api-tutorial-with-mysql-jpa-hibernate/
	public AccountVo updateAccountDetails(AccountVo newAcctDetails) throws Exception {
		logger.info("Checking the whether the account exists or not");
		AccountVo accountDetails = accountRepo.findAccountByAccountNumber(newAcctDetails.getAccountNumber());
		if(null != accountDetails){
			logger.info("Account details are-->" + accountDetails.toString());
			AccountTransactionVO transVo = new AccountTransactionVO("EDIT_ACCOUNT", 0, "", "Change of the account type from "+accountDetails.getAccounttype()+" to "+newAcctDetails.getAccounttype(), accountDetails);
			accountDetails.setAccounttype(newAcctDetails.getAccounttype());
			accountRepo.save(accountDetails);
			//
			transRepo.save(transVo);
			return accountDetails;
		} else {
			throw new AccountNotExistsException(newAcctDetails.getAccountNumber());
		}
		
	}
	
	
	public AccountVo getAccountTrans(String accountNumber) throws AccountNotExistsException{
		logger.info("Checking the whether the account exists or not");
		AccountVo accountDetails = accountRepo.findAccountByAccountNumber(accountNumber);
		if(null != accountDetails){
			List<AccountTransactionVO> findAllByAccountNumber = transRepo.findByAccountId(accountDetails.getId());
			accountDetails.getAccountTran().addAll(findAllByAccountNumber);
			return accountDetails;
		} else {
			throw new AccountNotExistsException(accountNumber);
		}
		
	}
}
