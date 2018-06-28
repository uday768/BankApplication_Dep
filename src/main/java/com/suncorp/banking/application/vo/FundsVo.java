package com.suncorp.banking.application.vo;

public class FundsVo {

	private String account, targetaccount;
	private double fund;
	private boolean sameBankFlag;

	public FundsVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FundsVo(String account, String targetaccount, double fund, boolean sameBankFlag) {
		super();
		this.account = account;
		this.targetaccount = targetaccount;
		this.fund = fund;
		this.sameBankFlag = sameBankFlag;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTargetaccount() {
		return targetaccount;
	}

	public void setTargetaccount(String targetaccount) {
		this.targetaccount = targetaccount;
	}

	public double getFund() {
		return fund;
	}

	public void setFund(double fund) {
		this.fund = fund;
	}

	public boolean isSameBankFlag() {
		return sameBankFlag;
	}

	public void setSameBankFlag(boolean sameBankFlag) {
		this.sameBankFlag = sameBankFlag;
	}

}
