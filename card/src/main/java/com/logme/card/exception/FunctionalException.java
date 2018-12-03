package com.logme.card.exception;

public class FunctionalException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public FunctionalException(String message) {
		super(message);
	}

	public FunctionalException(String format, Object... vars) {
		super(String.format(format, vars));
	}
}
