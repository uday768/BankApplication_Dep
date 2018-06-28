package com.suncorp.banking.application.controller;

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
import com.suncorp.banking.application.vo.AccountVo;
import com.suncorp.banking.application.vo.FundsVo;

@RestController
@RequestMapping("/account")
public class BankAccountController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountService accountService;
	
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<AccountResponseVO> createAccount1(@RequestBody AccountVo accountVo){
		logger.info("Inside the create acccount controller method");
		accountService.createAccount(accountVo);
		return ResponseEntity.status(HttpStatus.CREATED).body(new AccountResponseVO(accountVo.getAccountNumber(), accountVo.getAccounttype()));
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public AccountVo editAccount(@RequestBody AccountVo accountVo) throws Exception {
		logger.info("Inside the update account type service for the account number:"+accountVo.getAccountNumber());
		return accountService.updateAccountDetails(accountVo);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public AccountVo fetchAccountTransactions(@RequestBody FundsVo transVo) throws AccountNotExistsException{
		logger.info("");
		return accountService.getAccountTrans(transVo.getAccount());
	}
}
