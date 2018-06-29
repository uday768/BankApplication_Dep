package com.suncorp.banking.application.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.suncorp.banking.application.exceptions.AccountNotExistsException;
import com.suncorp.banking.application.exceptions.BeneficiaryNotFoundException;
import com.suncorp.banking.application.exceptions.InSufficientFundsException;
import com.suncorp.banking.application.repo.AccountRepository;
import com.suncorp.banking.application.repo.AccountTransactionRepository;
import com.suncorp.banking.application.repo.BeneficiaryRepository;
import com.suncorp.banking.application.vo.AccountTransactionVO;
import com.suncorp.banking.application.vo.AccountVo;
import com.suncorp.banking.application.vo.BeneficiaryVO;
import com.suncorp.banking.application.vo.FundsVo;
import com.suncorp.banking.application.vo.TransactionResponse;

@Service
@Scope(value = "singleton")
public class FundsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private AccountTransactionRepository transRepo;

	@Autowired
	private BeneficiaryRepository benefRepo;
	
	@Transactional
	public synchronized TransactionResponse depositFunds(FundsVo fundDetails) throws AccountNotExistsException {
		logger.info("Checking the whether the account exists or not");
		AccountVo accountFundDetails = accountRepo.findAccountByAccountnumber(fundDetails.getAccountnumber());
		if(null != accountFundDetails){
			logger.info("Account details are-->" + accountFundDetails.toString());
			double updatedBalance = accountFundDetails.getBalance() + fundDetails.getFund();
			accountFundDetails.setBalance(updatedBalance);
			String refId = generateRefId();
			AccountTransactionVO transVo = new AccountTransactionVO("DEPOSIT", fundDetails.getFund(), refId, "Amount deposited into the account", accountFundDetails);
			accountRepo.save(accountFundDetails);
			transRepo.save(transVo);
			return new TransactionResponse(refId, "Deposit request has been processed. Amount is creted to the account:"+accountFundDetails.getAccountNumber());
		} else {
			throw new AccountNotExistsException(fundDetails.getAccountnumber());
		}
		
	}

	@Transactional
	public synchronized TransactionResponse withDrawFunds(FundsVo fundDetails) throws AccountNotExistsException, InSufficientFundsException {
		logger.info("Checking the whether the account exists or not");
		AccountVo accountFundDetails = accountRepo.findAccountByAccountnumber(fundDetails.getAccountnumber());
		if (null != accountFundDetails) {
			if (accountFundDetails.getBalance() > fundDetails.getFund()) {
				logger.info("Account details are-->" + accountFundDetails.toString());
				double updatedBalance = accountFundDetails.getBalance() - fundDetails.getFund();
				accountFundDetails.setBalance(updatedBalance);
				accountRepo.save(accountFundDetails);
				String refId = generateRefId();
				AccountTransactionVO transVo = new AccountTransactionVO("WITHDRAWL", fundDetails.getFund(), refId, "Amount withdrawl from the account", accountFundDetails);
				accountRepo.save(accountFundDetails);
				transRepo.save(transVo);
				return new TransactionResponse(refId, "Withdrawl request has been processed.");
			} else {
				throw new InSufficientFundsException(fundDetails.getAccountnumber());
			}
		} else {
			throw new AccountNotExistsException(fundDetails.getAccountnumber());
		}
	}

	@Transactional
	public TransactionResponse trasnferFunds(FundsVo fundDetails) throws AccountNotExistsException, InSufficientFundsException, BeneficiaryNotFoundException {
		logger.info("Checking the whether the account exists or not");
		AccountVo accountFundDetails = accountRepo.findAccountByAccountnumber(fundDetails.getAccountnumber());
		List<BeneficiaryVO> beneficiaryDetails = benefRepo.find(fundDetails.getTargetaccountnumber(), fundDetails.getAccountnumber());
		if (null != accountFundDetails) {
			if(null != beneficiaryDetails && beneficiaryDetails.size()>0){
				if (accountFundDetails.getBalance() > fundDetails.getFund()) {
					logger.info("Account details are-->" + accountFundDetails.toString());
					double updatedBalance = accountFundDetails.getBalance() - fundDetails.getFund();
					accountFundDetails.setBalance(updatedBalance);
					String refId = generateRefId();
					AccountTransactionVO transVo = new AccountTransactionVO("FUND_TRANSFER", fundDetails.getFund(), refId, "Amount transfered from "+fundDetails.getAccountnumber()+" to "+fundDetails.getTargetaccountnumber(), accountFundDetails);
					accountRepo.save(accountFundDetails);
					transRepo.save(transVo);
					return new TransactionResponse(refId, "Fund Trasnfer request has been processed. Amount has been debited from the account:"+fundDetails.getAccountnumber());
				} else {
					throw new InSufficientFundsException(fundDetails.getAccountnumber());
				}
			} else {
				throw new BeneficiaryNotFoundException(fundDetails.getAccountnumber(), fundDetails.getTargetaccountnumber());
			}
		} else {
			throw new AccountNotExistsException(fundDetails.getAccountnumber());
		}
	}
	
	
	private String generateRefId(){
		return new Timestamp(new Date().getTime()).toString().replace(":", "")
				.replace(".", "").replace(" ", "").replace("-", "");
	}

}
