package com.suncorp.banking.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.suncorp.banking.application.exceptions.AccountNotExistsException;
import com.suncorp.banking.application.service.AccountService;
import com.suncorp.banking.application.vo.AccountResponseVO;
import com.suncorp.banking.application.vo.AccountTransactionVO;
import com.suncorp.banking.application.vo.AccountVo;
import com.suncorp.banking.application.vo.FundsVo;
import com.suncorp.banking.application.vo.TransactionResponse;

@RestController
@RequestMapping("/account")
public class BankAccountController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountService accountService;
	
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<AccountResponseVO> createAccount(@RequestBody AccountVo accountVo){
		logger.info("Inside the create acccount controller method");
		AccountResponseVO createAccountResponse = accountService.createAccount(accountVo);
		return ResponseEntity.status(HttpStatus.CREATED).body(createAccountResponse);
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<TransactionResponse> editAccount(@RequestBody AccountVo accountVo) throws AccountNotExistsException {
		logger.info("Inside the update account type service for the account number:"+accountVo.getAccountNumber());
		return ResponseEntity.status(HttpStatus.OK).body(accountService.updateAccountDetails(accountVo));
	}
	
	@ResponseBody
	@RequestMapping(value = "/viewtransactions", method = RequestMethod.POST)
	public ResponseEntity<?> fetchAccountTransactionDetails(@RequestBody FundsVo transVo) throws AccountNotExistsException{
		logger.info("Fetching the transaction details for the account number:"+transVo.getAccountnumber());
		List<AccountTransactionVO> accountTrans = accountService.getAccountTrans(transVo.getAccountnumber());
		if(null != accountTrans && accountTrans.size()>0){
			return ResponseEntity.status(HttpStatus.OK).body(accountTrans);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new TransactionResponse("NO_DATA_FOUND", "No Transactions were performed on this account."));
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/viewaccount", method = RequestMethod.POST)
	public ResponseEntity<?> fetchAccountDetails(@RequestBody FundsVo accountVo) throws AccountNotExistsException{
		logger.info("Fetching the transaction details for the account number:"+accountVo.getAccountnumber());
		AccountVo accountTrans = accountService.getAccountDetails(accountVo.getAccountnumber());
		return ResponseEntity.status(HttpStatus.OK).body(accountTrans);
	}
	
}
