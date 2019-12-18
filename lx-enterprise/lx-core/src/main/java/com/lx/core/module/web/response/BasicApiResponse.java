package com.lx.core.module.web.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.assertj.core.util.DateUtil;

public class BasicApiResponse<T> {
    @JsonProperty(
            index = 1
    )
    private Integer code;
    @JsonProperty(
            index = 2
    )
    private String message;
    @JsonProperty(
            index = 3
    )
    private String timestamp = DateUtil.now().toString();
    @JsonProperty(
            index = 4
    )
    private T data;
    protected static final int STATUS_OK_CODE = 200;
    protected static final String STATUS_OK_MSG_ = "Success.";

    public BasicApiResponse() {
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public T getData() {
        return this.data;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    public void setData(final T data) {
        this.data = data;
    }



    public String toString() {
        return "BasicApiResponse(code=" + this.getCode() + ", message=" + this.getMessage() + ", timestamp=" + this.getTimestamp() + ", data=" + this.getData() + ")";
    }
}
