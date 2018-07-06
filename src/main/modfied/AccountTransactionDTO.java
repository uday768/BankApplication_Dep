package com.suncorp.banking.application.dto;

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

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bank_accounts_transactions")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdDate" }, allowGetters = true)
public class AccountTransactionDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	@JsonIgnore
	private long id;

	@Column(name = "type_of_transaction")
	private String typeOfTrans;

	@Column(name = "fund_value")
	private double fund;

	@Column(name = "reference_id")
	private String referenceId;

	@Column(name = "reference_msg")
	private String referenceMsg;

	@Column(name = "record_created_date", nullable = false, updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd-MM-yyyy")
	private Date createdDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id")
	@JsonIgnore
	private AccountDTO account;

	public AccountTransactionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountTransactionDTO(String typeOfTrans, String referenceId, String referenceMsg,
			AccountDTO account) {
		super();
		this.typeOfTrans = typeOfTrans;
		this.referenceId = referenceId;
		this.referenceMsg = referenceMsg;
		this.account = account;
	}

	public AccountTransactionDTO(String typeOfTrans, double fund, String referenceId, String referenceMsg,
			AccountDTO account) {
		super();
		this.typeOfTrans = typeOfTrans;
		this.fund = fund;
		this.referenceId = referenceId;
		this.referenceMsg = referenceMsg;
		this.account = account;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTypeOfTrans() {
		return typeOfTrans;
	}

	public void setTypeOfTrans(String typeOfTrans) {
		this.typeOfTrans = typeOfTrans;
	}

	public double getFund() {
		return fund;
	}

	public void setFund(double fund) {
		this.fund = fund;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getReferenceMsg() {
		return referenceMsg;
	}

	public void setReferenceMsg(String referenceMsg) {
		this.referenceMsg = referenceMsg;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public AccountDTO getAccount() {
		return account;
	}

	public void setAccount(AccountDTO account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "AccountTransactionVO [id=" + id + ", typeOfTrans=" + typeOfTrans + ", fund=" + fund + ", referenceId="
				+ referenceId + ", referenceMsg=" + referenceMsg + ", createdDate=" + createdDate + ", account="
				+ account + "]";
	}

}
