package com.br4dev.security;

public class MethodDigestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MethodDigestException() {
		super();
	}

	public MethodDigestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MethodDigestException(String message, Throwable cause) {
		super(message, cause);
	}

	public MethodDigestException(String message) {
		super(message);
	}

	public MethodDigestException(Throwable cause) {
		super(cause);
	}

}
