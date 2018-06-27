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

import com.suncorp.banking.application.service.AccountService;
import com.suncorp.banking.application.vo.AccountResponseVO;
import com.suncorp.banking.application.vo.AccountVo;

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
}
