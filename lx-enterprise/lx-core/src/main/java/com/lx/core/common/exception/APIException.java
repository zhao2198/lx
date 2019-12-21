
package com.lx.core.common.exception;


public class APIException extends ServiceException {

	private static final long serialVersionUID = -7239298948780522119L;

	public Status status = ResponseStatus.SYSTEM_ERROR;

	public APIException() {
	}

	public APIException(Status status) {
		this.status = status;
	}

	public APIException(String message) {
		super(message);
	}

	public APIException(ResponseStatus status, String message) {
		super(message);
		this.status = status;
	}

	@Override
	public Status getStatus() {
		return status;
	}
}