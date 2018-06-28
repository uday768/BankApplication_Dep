package com.suncorp.banking.application.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.suncorp.banking.application.exceptions.AccountNotExistsException;
import com.suncorp.banking.application.exceptions.InSufficientFundsException;
import com.suncorp.banking.application.repo.AccountRepository;
import com.suncorp.banking.application.repo.AccountTransactionRepository;
import com.suncorp.banking.application.vo.AccountTransactionVO;
import com.suncorp.banking.application.vo.AccountVo;
import com.suncorp.banking.application.vo.FundsVo;

@Service
@Scope(value = "singleton")
public class FundsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private AccountTransactionRepository transRepo;

	@Transactional
	public synchronized AccountVo depositFunds(FundsVo fundDetails) throws AccountNotExistsException {
		logger.info("Checking the whether the account exists or not");
		AccountVo accountFundDetails = accountRepo.findAccountByAccountNumber(fundDetails.getAccount());
		if(null != accountFundDetails){
			logger.info("Account details are-->" + accountFundDetails.toString());
			double updatedBalance = accountFundDetails.getBalance() + fundDetails.getFund();
			accountFundDetails.setBalance(updatedBalance);
			AccountTransactionVO transVo = new AccountTransactionVO("DEPOSIT", fundDetails.getFund(), "", "Amount deposited into the account", accountFundDetails);
			accountRepo.save(accountFundDetails);
			transRepo.save(transVo);
			return accountFundDetails;
		} else {
			throw new AccountNotExistsException(fundDetails.getAccount());
		}
		
	}

	@Transactional
	public synchronized AccountVo withDrawFunds(FundsVo fundDetails) throws AccountNotExistsException, InSufficientFundsException {
		logger.info("Checking the whether the account exists or not");
		AccountVo accountFundDetails = accountRepo.findAccountByAccountNumber(fundDetails.getAccount());
		if (null != accountFundDetails) {
			if (accountFundDetails.getBalance() > fundDetails.getFund()) {
				logger.info("Account details are-->" + accountFundDetails.toString());
				double updatedBalance = accountFundDetails.getBalance() - fundDetails.getFund();
				accountFundDetails.setBalance(updatedBalance);
				accountRepo.save(accountFundDetails);
				AccountTransactionVO transVo = new AccountTransactionVO("WITHDRAWL", fundDetails.getFund(), "", "Amount withdrawl from the account", accountFundDetails);
				accountRepo.save(accountFundDetails);
				transRepo.save(transVo);
				return accountFundDetails;
			} else {
				throw new InSufficientFundsException(fundDetails.getAccount());
			}
		} else {
			throw new AccountNotExistsException(fundDetails.getAccount());
		}
	}

	@Transactional
	public void trasnferFunds(FundsVo fundDetails) throws AccountNotExistsException, InSufficientFundsException {
		logger.info("Checking the whether the account exists or not");
		AccountVo accountFundDetails = accountRepo.findAccountByAccountNumber(fundDetails.getAccount());
		if (null != accountFundDetails) {
			if (accountFundDetails.getBalance() > fundDetails.getFund()) {
				logger.info("Account details are-->" + accountFundDetails.toString());
				double updatedBalance = accountFundDetails.getBalance() - fundDetails.getFund();
				accountFundDetails.setBalance(updatedBalance);
				AccountTransactionVO transVo = new AccountTransactionVO("FUND_TRANSFER", fundDetails.getFund(), "", "Amount transfered from "+fundDetails.getAccount()+" to "+fundDetails.getTargetaccount(), accountFundDetails);
				accountRepo.save(accountFundDetails);
				transRepo.save(transVo);
			} else {
				throw new InSufficientFundsException(fundDetails.getAccount());
			}
		} else {
			throw new AccountNotExistsException(fundDetails.getAccount());
		}
	}
}
