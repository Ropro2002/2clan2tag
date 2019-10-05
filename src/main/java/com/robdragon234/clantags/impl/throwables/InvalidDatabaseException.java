package com.robdragon234.clantags.impl.throwables;

public class InvalidDatabaseException extends RuntimeException {
	public InvalidDatabaseException() {
	}
	
	public InvalidDatabaseException(String message) {
		super(message);
	}
	
	public InvalidDatabaseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidDatabaseException(Throwable cause) {
		super(cause);
	}
	
	public InvalidDatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
