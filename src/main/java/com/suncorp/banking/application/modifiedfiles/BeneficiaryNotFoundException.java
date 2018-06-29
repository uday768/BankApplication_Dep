package com.suncorp.banking.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BeneficiaryNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeneficiaryNotFoundException(String accountNumber, String beneficiaryAccountNumber) {
		super("Account number:"+beneficiaryAccountNumber+ " is not tagged as beneficiary for the " + accountNumber);
	}
}
