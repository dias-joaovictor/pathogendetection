package org.nal.pathogendetection.exception;

public class ProcessException extends RuntimeException {

	private static final long serialVersionUID = -7067599053526791289L;

	public ProcessException(final String message, final Throwable throwable) {
		super(message, throwable);
	}

	public ProcessException(final String message) {
		super(message);
	}

	public ProcessException(final Throwable throwable) {
		super(throwable);
	}

}
