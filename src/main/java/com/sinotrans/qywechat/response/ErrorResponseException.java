package com.sinotrans.qywechat.response;

public class ErrorResponseException extends Exception{
	private static final long serialVersionUID = 8458844252717090322L;
	
	public ErrorResponseException() {
		super();
	}
	
	public ErrorResponseException(String msg) {
		super(msg);
	}
	
	public ErrorResponseException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public ErrorResponseException(Throwable cause) {
		super(cause);
	}
}
