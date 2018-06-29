package com.suncorp.banking.application.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "bank_accounts_beneficiary")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createddate" }, allowGetters = true)
public class BeneficiaryVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "beneficiary_id")
	@JsonIgnore
	private long id;

	@Column(name = "beneficiary_name")
	private String name;

	@Column(name = "beneficiary_account_number")
	private String benefaccountnumber;

	@Column(name = "same_bank_flag")
	private boolean samebankflag;

	@Column(name = "ifsc_code")
	private String ifsccode;

	@Column(name = "beneficiary_email_id")
	private String emailid;

	@Column(name = "record_created_date", nullable = false, updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd-MM-yyyy HH:MM:SS")
	private Date createddate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id")
	@JsonIgnore
	private AccountVo account;

	@Transient
	@JsonProperty(access = Access.WRITE_ONLY)
	private String accountnumber;

	public BeneficiaryVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BeneficiaryVO(long id, String name, String benefaccountnumber, boolean samebankflag, String ifsccode,
			String emailid, Date createdDate, AccountVo account, String accountnumber) {
		super();
		this.id = id;
		this.name = name;
		this.benefaccountnumber = benefaccountnumber;
		this.samebankflag = samebankflag;
		this.ifsccode = ifsccode;
		this.emailid = emailid;
		this.createddate = createdDate;
		this.account = account;
		this.accountnumber = accountnumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBenefaccountnumber() {
		return benefaccountnumber;
	}

	public void setBenefaccountnumber(String benefaccountnumber) {
		this.benefaccountnumber = benefaccountnumber;
	}

	public boolean isSamebankflag() {
		return samebankflag;
	}

	public void setSamebankflag(boolean samebankflag) {
		this.samebankflag = samebankflag;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public Date getCreatedDate() {
		return createddate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createddate = createdDate;
	}

	public AccountVo getAccount() {
		return account;
	}

	public void setAccount(AccountVo account) {
		this.account = account;
	}

	@JsonIgnore
	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	@Override
	public String toString() {
		return "BeneficiaryVO [id=" + id + ", name=" + name + ", benefaccountnumber=" + benefaccountnumber
				+ ", samebankflag=" + samebankflag + ", ifsccode=" + ifsccode + ", emailid=" + emailid
				+ ", createdDate=" + createddate + ", account=" + account + ", accountnumber=" + accountnumber + "]";
	}

}
