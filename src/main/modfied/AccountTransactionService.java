package com.suncorp.banking.application.service;

import java.util.List;

import com.suncorp.banking.application.dto.AccountTransactionDTO;
import com.suncorp.banking.application.exceptions.AccountNotExistsException;

public interface AccountTransactionService {

	public List<AccountTransactionDTO> getAccountTrans(String accountNumber) throws AccountNotExistsException;

}
