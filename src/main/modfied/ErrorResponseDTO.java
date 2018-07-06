package com.suncorp.banking.application.dto;

import java.util.Set;

public class ErrorResponseDTO {
	private String message;

	private Set<String> details;

	public ErrorResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorResponseDTO(String message, Set<String> details) {
		super();
		this.message = message;
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Set<String> getDetails() {
		return details;
	}

	public void setDetails(Set<String> details) {
		this.details = details;
	}

}
