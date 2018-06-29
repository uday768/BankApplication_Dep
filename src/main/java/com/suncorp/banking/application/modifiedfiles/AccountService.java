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
import com.suncorp.banking.application.vo.AccountResponseVO;
import com.suncorp.banking.application.vo.AccountTransactionVO;
import com.suncorp.banking.application.vo.AccountVo;
import com.suncorp.banking.application.vo.TransactionResponse;

@Service
public class AccountService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AccountRepository accountRepo; 
	
	@Autowired
	AccountTransactionRepository transRepo;
	
	public AccountResponseVO createAccount(AccountVo accountDetails){
		logger.info("Creating the account for the user:"+accountDetails.getFirstname());
		Timestamp timestamp = new Timestamp(new Date().getTime());
		String accountNumberFeed = accountDetails.getFirstname() + accountDetails.getLastname() + timestamp.toString();
		SecureRandom random = new SecureRandom(accountNumberFeed.getBytes());
		accountDetails.setAccountNumber(Integer.toString(1000000000 + random.nextInt(90000000)));
		accountRepo.save(accountDetails);
		return new AccountResponseVO(accountDetails.getAccountNumber(), accountDetails.getAccounttype(), generateRefId(), "Request for creating the account has been processed.");
	}
	
	@Transactional
	public TransactionResponse updateAccountDetails(AccountVo newAcctDetails) throws AccountNotExistsException {
		logger.info("Checking the whether the account exists or not");
		AccountVo accountDetails = accountRepo.findAccountByAccountnumber(newAcctDetails.getAccountNumber());
		if(null != accountDetails){
			logger.info("Account details are-->" + accountDetails.toString());
			String refId = generateRefId();
			AccountTransactionVO transVo = new AccountTransactionVO("EDIT_ACCOUNT", 0, refId, "Change of the account type from "+accountDetails.getAccounttype()+" to "+newAcctDetails.getAccounttype(), accountDetails);
			accountDetails.setAccounttype(newAcctDetails.getAccounttype());
			accountRepo.save(accountDetails);
			transRepo.save(transVo);
			return new TransactionResponse(refId, "Request for changing the account type for the account number "+accountDetails.getAccountNumber()+" has been processed.");
		} else {
			throw new AccountNotExistsException(newAcctDetails.getAccountNumber());
		}
	}
	
	public List<AccountTransactionVO> getAccountTrans(String accountNumber) throws AccountNotExistsException{
		logger.info("Checking the whether the account exists or not");
		AccountVo accountDetails = accountRepo.findAccountByAccountnumber(accountNumber);
		if(null != accountDetails){
			List<AccountTransactionVO> findAllByAccountNumber = transRepo.findByAccountId(accountDetails.getId());
			return findAllByAccountNumber;
		} else {
			throw new AccountNotExistsException(accountNumber);
		}
	}
	
	
	
	public AccountVo getAccountDetails(String accountNumber) throws AccountNotExistsException{
		logger.info("Checking the whether the account exists or not");
		AccountVo accountDetails = accountRepo.findAccountByAccountnumber(accountNumber);
		if(null != accountDetails){
			return accountDetails;
		} else {
			throw new AccountNotExistsException(accountNumber);
		}
	}
	
	
	private String generateRefId(){
		return new Timestamp(new Date().getTime()).toString().replace(":", "")
				.replace(".", "").replace(" ", "").replace("-", "");
	}
}
