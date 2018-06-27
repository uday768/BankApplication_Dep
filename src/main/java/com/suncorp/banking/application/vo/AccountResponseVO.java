package com.suncorp.banking.application.vo;

public class AccountResponseVO {

	private String accountNumber, accountType;

	public String getAccountNumber() {
		return accountNumber;
	}

	public AccountResponseVO(String accountNumber, String accountType) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "AccountResponseVO [accountNumber=" + accountNumber + ", accountType=" + accountType + "]";
	}

}
