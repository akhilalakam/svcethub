package com.svcethub.excepton;

public class SvcethubException extends Exception{
	
	private Long status;
	private String message;
	private Error error;
	
	public SvcethubException(Long status, String message, Error error) {
		super();
		this.status = status;
		this.message = message;
		this.error = error;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}
	 
}
