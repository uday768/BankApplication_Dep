package com.suncorp.banking.application.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.suncorp.banking.application.dto.AccountDTO;
import com.suncorp.banking.application.dto.FundsDTO;
import com.suncorp.banking.application.dto.TransactionResponseDTO;
import com.suncorp.banking.application.exceptions.AccountNotExistsException;
import com.suncorp.banking.application.exceptions.InSufficientFundsException;
import com.suncorp.banking.application.service.AccountService;
import com.suncorp.banking.application.validation.groups.NullNotAllowed;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountService accountService;

	@ResponseBody
	@GetMapping("{accountNumber}")
	public ResponseEntity<AccountDTO> fetchAccountDetails(@PathVariable String accountNumber)
			throws AccountNotExistsException {
		logger.info("Fetching the transaction details for the account number:" + accountNumber);
		AccountDTO accountTrans = accountService.getAccountDetails(accountNumber);
		return ResponseEntity.status(HttpStatus.OK).body(accountTrans);
	}

	@ResponseBody
	@PostMapping
	public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO accountRequest) {
		logger.info("Inside the create acccount controller method");
		AccountDTO createAccountResponse = accountService.createAccount(accountRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(createAccountResponse);
	}

	@ResponseBody
	@PatchMapping("{accountNumber}")
	public ResponseEntity<AccountDTO> editAccount(@PathVariable String accountNumber,
			@Validated(NullNotAllowed.class) @RequestBody AccountDTO accountRequest) throws AccountNotExistsException {
		logger.info("Inside the update account type service for the account number:" + accountNumber);
		return ResponseEntity.status(HttpStatus.OK)
				.body(accountService.updateAccountDetails(accountNumber, accountRequest));
	}

	@ResponseBody
	@PostMapping("{accountNumber}")
	public ResponseEntity<?> performFundTransaction(@PathVariable String accountNumber,
			@Valid @RequestBody FundsDTO fundsRequest) throws AccountNotExistsException, InSufficientFundsException {
		logger.info("Performing the account related trasactions for the account number:" + accountNumber);
		TransactionResponseDTO fundTransactionResponse = accountService.fundTransactions(accountNumber, fundsRequest);
		return ResponseEntity.status(HttpStatus.OK).body(fundTransactionResponse);
	}
}
