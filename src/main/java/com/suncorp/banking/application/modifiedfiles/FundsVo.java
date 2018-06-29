package com.suncorp.banking.application.vo;

public class FundsVo {

	private String accountnumber, targetaccountnumber;
	private double fund;

	public FundsVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FundsVo(String accountnumber, String targetaccountnumber, double fund, boolean sameBankFlag) {
		super();
		this.accountnumber = accountnumber;
		this.targetaccountnumber = targetaccountnumber;
		this.fund = fund;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getTargetaccountnumber() {
		return targetaccountnumber;
	}

	public void setTargetaccountnumber(String targetaccountnumber) {
		this.targetaccountnumber = targetaccountnumber;
	}

	public double getFund() {
		return fund;
	}

	public void setFund(double fund) {
		this.fund = fund;
	}

}
