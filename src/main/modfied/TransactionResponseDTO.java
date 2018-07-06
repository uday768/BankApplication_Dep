package com.suncorp.banking.application.dto;

public class TransactionResponseDTO {

	private String referenceid, referencemsg;

	public TransactionResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionResponseDTO(String referenceid, String referencemsg) {
		super();
		this.referenceid = referenceid;
		this.referencemsg = referencemsg;
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

}
