
package com.lx.core.common.exception;

public class TokenException extends ServiceException {

    private static final long serialVersionUID = -7239298948780522119L;

    public Status status = ResponseStatus.TOKEN_AUTHENTICATION_FAILURE;

    public TokenException() {
        super.status = this.status;
    }

}