package com.lx.core.module.web.response;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiErrorResponse<T> {
    @JsonProperty(
            value = "code",
            index = 1
    )
    private Integer status;
    @JsonProperty(
            index = 2
    )
    private String exception;
    @JsonProperty(
            index = 3
    )
    private String message;
    @JsonProperty(
            index = 4
    )
    private String path;
    @JsonProperty(
            index = 5
    )
    private String timestamp;
    @JsonProperty(
            index = 6
    )
    private T errorData;

    public ApiErrorResponse() {
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getException() {
        return this.exception;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPath() {
        return this.path;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public T getErrorData() {
        return this.errorData;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public void setException(final String exception) {
        this.exception = exception;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    public void setErrorData(final T errorData) {
        this.errorData = errorData;
    }


    protected boolean canEqual(final Object other) {
        return other instanceof ApiErrorResponse;
    }


    public String toString() {
        return "ApiErrorResponse(status=" + this.getStatus() + ", exception=" + this.getException() + ", message=" + this.getMessage() + ", path=" + this.getPath() + ", timestamp=" + this.getTimestamp() + ", errorData=" + this.getErrorData() + ")";
    }
}
