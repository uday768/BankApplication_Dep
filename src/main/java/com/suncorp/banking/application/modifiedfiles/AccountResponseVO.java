package com.suncorp.banking.application.vo;

public class AccountResponseVO {

	private String accountnumber, accounttype, referenceid, referencemsg;

	public AccountResponseVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountResponseVO(String accountnumber, String accounttype, String referenceid, String referencemsg) {
		super();
		this.accountnumber = accountnumber;
		this.accounttype = accounttype;
		this.referenceid = referenceid;
		this.referencemsg = referencemsg;
	}

	public AccountResponseVO(String accountnumber, String accounttype) {
		super();
		this.accountnumber = accountnumber;
		this.accounttype = accounttype;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public String getReferenceid() {
		return referenceid;
	}

	public void setReferenceid(String referenceid) {
		this.referenceid = referenceid;
	}

	public String getReferencemsg() {
		return referencemsg;
	}

	public void setReferencemsg(String referencemsg) {
		this.referencemsg = referencemsg;
	}

	@Override
	public String toString() {
		return "AccountResponseVO [accountnumber=" + accountnumber + ", accounttype=" + accounttype + ", referenceid="
				+ referenceid + ", referencemsg=" + referencemsg + "]";
	}

}
