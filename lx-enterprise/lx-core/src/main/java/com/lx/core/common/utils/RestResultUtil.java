package com.lx.core.common.utils;


import com.lx.core.common.exception.ResponseStatus;
import com.lx.core.common.exception.Status;

public class RestResultUtil {

    /**
     * normal
     *
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> getResult(int code, T data, String message) {
        RestResult<T> result = new RestResult<T>();
        result.setCode(code);
        result.setData(data);
        result.setMessage(message);

        return result;
    }

    public static <T> RestResult<T> getResult(Status status, T data) {
        RestResult<T> result = new RestResult<T>();
        result.setCode(status.getCode());
        result.setData(data);
        result.setMessage(status.getMessage());
        return result;
    }

    /**
     * @param status
     * @return
     */
    public static <T> RestResult<T> getResult(Status status) {
        return getResult(status.getCode(), null, status.getMessage());
    }

    /**
     * @param status
     * @return
     */
    public static <T> RestResult<T> getResult(Status status, String message) {
        return getResult(status.getCode(), null, message);
    }

    /**
     * 成功
     *
     * @return
     */
    public static <T> RestResult<T> ok() {
        return getResult(ResponseStatus.OK);
    }

    /**
     * success
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> ok(T data) {
        return getResult(ResponseStatus.OK, data);
    }

    /**
     * 失败
     *
     * @return
     */
    public static <T> RestResult<T> failed() {
        return getResult(ResponseStatus.FAILED);
    }

    public static <T> RestResult<T> failed(String message) {
        return getResult(ResponseStatus.FAILED, message);
    }

}
