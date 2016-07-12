package com.saulo.borges.exception;

public class AppException extends Exception {

	private static final long serialVersionUID = -4454531872952074931L;

	Integer status;

	int code;

	public AppException(int status, int code, String message) {
		super(message);
		this.status = status;
		this.code = code;
	}

	public AppException() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}


}