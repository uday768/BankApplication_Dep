package com.suncorp.banking.application.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.suncorp.banking.application.dto.AccountTransactionDTO;
import com.suncorp.banking.application.dto.TransactionResponseDTO;
import com.suncorp.banking.application.exceptions.AccountNotExistsException;
import com.suncorp.banking.application.service.AccountTransactionService;

@RestController
@RequestMapping("/accounts/{accountNumber}/transactions")
public class BankAccountTransactionController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountTransactionService accountTransactionService;

	@ResponseBody
	@GetMapping
	public ResponseEntity<?> fetchAccountTransactionDetails(@PathVariable String accountNumber)
			throws AccountNotExistsException {
		logger.info("Fetching the transaction details for the account number:" + accountNumber);
		List<AccountTransactionDTO> accountTrans = accountTransactionService.getAccountTrans(accountNumber);
		if (null != accountTrans && accountTrans.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(accountTrans);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new TransactionResponseDTO("NO_DATA_FOUND", "No Transactions were performed on this account."));
		}
	}
	
}
