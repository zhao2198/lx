
package com.lx.core.common.exception;


public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1029422831178946627L;

    protected Status status = ResponseStatus.SYSTEM_ERROR;

    public BaseException() {
    }

    public BaseException(ResponseStatus status) {
        super();
        this.status = status;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public Integer getCode() {
        return status.getCode();
    }

    public Status getStatus() {
        return status;
    }


}
