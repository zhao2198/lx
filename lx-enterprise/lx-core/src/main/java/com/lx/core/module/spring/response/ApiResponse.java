package com.lx.core.module.spring.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lx.core.module.web.response.BasicApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse<T> extends BasicApiResponse<T> {
    public ApiResponse() {
    }

    private ResponseEntity<ApiResponse<T>> buildResponseEntity() {
        return new ResponseEntity(this, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse<T>> ok() {
        this.setCode(200);
        this.setMessage("Success.");
        return this.buildResponseEntity();
    }

    public ResponseEntity<ApiResponse<T>> ok(T t) {
        this.setCode(200);
        this.setMessage("Success.");
        this.setData(t);
        return this.buildResponseEntity();
    }

    @JsonIgnore
    public boolean isOk() {
        return null != this.getCode() && this.getCode().equals(200);
    }

    public static ResponseEntity success() {
        return (new ApiResponse()).ok();
    }
}

