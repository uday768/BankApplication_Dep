package com.suncorp.banking.application.service;

import com.suncorp.banking.application.dto.AccountDTO;
import com.suncorp.banking.application.dto.FundsDTO;
import com.suncorp.banking.application.dto.TransactionResponseDTO;
import com.suncorp.banking.application.exceptions.AccountNotExistsException;
import com.suncorp.banking.application.exceptions.InSufficientFundsException;

public interface AccountService {

	public AccountDTO createAccount(AccountDTO accountDetails);

	public AccountDTO updateAccountDetails(String accountNumber, AccountDTO newAcctDetails)
			throws AccountNotExistsException;


	public AccountDTO getAccountDetails(String accountNumber) throws AccountNotExistsException;

	public TransactionResponseDTO fundTransactions(String accountNumber, FundsDTO fundsDto)
			throws AccountNotExistsException, InSufficientFundsException;

}
