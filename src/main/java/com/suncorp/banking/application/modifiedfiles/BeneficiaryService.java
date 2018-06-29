package com.suncorp.banking.application.service;

import java.security.SecureRandom;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.suncorp.banking.application.exceptions.AccountNotExistsException;
import com.suncorp.banking.application.repo.AccountRepository;
import com.suncorp.banking.application.repo.AccountTransactionRepository;
import com.suncorp.banking.application.repo.BeneficiaryRepository;
import com.suncorp.banking.application.vo.AccountTransactionVO;
import com.suncorp.banking.application.vo.AccountVo;
import com.suncorp.banking.application.vo.BeneficiaryVO;
import com.suncorp.banking.application.vo.TransactionResponse;

@Service
@Scope(value = "singleton")
public class BeneficiaryService {

	@Autowired
	BeneficiaryRepository beneficiaryRepo;
	
	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	AccountTransactionRepository transRepo;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Transactional
	public TransactionResponse addBeneficiary(BeneficiaryVO beneficiaryDetails) throws AccountNotExistsException {
		logger.info("Adding the new beneficiary details for the account number "+beneficiaryDetails.getAccountnumber());
		AccountVo accountDetails = accountRepo.findAccountByAccountnumber(beneficiaryDetails.getAccountnumber());
		if(null != accountDetails){
			SecureRandom random = new SecureRandom(beneficiaryDetails.toString().getBytes());
			String referenceId =  Integer.toString(1000000 + random.nextInt(9000000));
			AccountTransactionVO transVo = new AccountTransactionVO("ADD_BENEFICIARY",referenceId , "New Benefiicary added with account number "+beneficiaryDetails.getBenefaccountnumber(), accountDetails);
			beneficiaryDetails.setAccount(accountDetails);
			beneficiaryRepo.save(beneficiaryDetails);
			transRepo.save(transVo);
			return new TransactionResponse(referenceId, "Beneficairy addition request has been for processed for the account number "+beneficiaryDetails.getAccountnumber());
		} else {
			throw new AccountNotExistsException(beneficiaryDetails.getAccountnumber());
		}
	}
	
	public List<BeneficiaryVO> getBeneficiaryList(String accountNumber) throws AccountNotExistsException{
		logger.info("Checking the whether the account exists or not");
		AccountVo accountDetails = accountRepo.findAccountByAccountnumber(accountNumber);
		if(null != accountDetails){
			List<BeneficiaryVO> beneficiaryList = beneficiaryRepo.findByAccountId(accountDetails.getId());
			return beneficiaryList;
		} else {
			throw new AccountNotExistsException(accountNumber);
		}
	}

}
