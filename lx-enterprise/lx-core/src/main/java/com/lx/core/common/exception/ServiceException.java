
package com.lx.core.common.exception;


public class ServiceException extends BaseException {

	private static final long serialVersionUID = 8184753188547987287L;

	public ServiceException() {
	}

	public ServiceException(ResponseStatus status) {
		super(status);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
