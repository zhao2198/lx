
package com.lx.core.common.exception;

import org.apache.commons.lang3.StringUtils;

public enum ResponseStatus implements Status {

    OK(10000, "成功", "成功"),

    FAILED(-1, "失败", "失败"),

    // 系统状态
    TOKEN_AUTHENTICATION_FAILURE(10001, "token验证失败"),

    SYSTEM_ERROR(10002, "系统错误"),

    MISS_REQUIRED_PARAMETE(10003, "缺失必选参数，请参考API文档"),

    PARAMETER_VALUE_ILLEGAL(10004, "参数值非法"),

    UNKNOWN_ERROR(10005, "未知错误"),

    NO_SUCH_REQUEST_HANDLING_METHOD(10006, "当前请求没有匹配的处理器"),

    HTTP_REQUEST_METHOD_NOT_SUPPORTED(10007, "请求方法不被支持"),

    PARAMETE_TYPE_MISMATCH(10008, "参数类型缺失"),

    HTTP_MEDIA_TYPE_NOT_SUPPORTED(10009, "http media type not supported"),

    HTTP_MEDIA_TYPE_NOT_ACCEPTABLE(10010, "Could not find acceptable representation"),

    MISSING_SERVLET_REQUEST_PARAMETER(10011, "Required parameter is not present"),

    SERVLET_REQUEST_BINDING(10012, "Fatal binding exception"),

    CONVERSION_NOT_SUPPORTED(10013, "Conversion not supported.",
            "Exception thrown when no suitable editor or converter can be found for a bean property"),

    HTTP_MESSAGE_NOT_READABLE(10014, "http message not readable."),

    HTTP_MESSAGE_NOT_WRITABLE(10015, "http message not writable."),

    METHOD_ARGUMENT_NOT_VALID(10016, "method argument not valid."),

    MISSING_SERVLET_REQUEST_PART(10017, "Required request part is not present."),

    NO_HANDLER_FOUND(10018, "No handler found"),

    CONSTRAINT_VIOLATION(10019, "constraint violation"),

    CLIENT_DEVICE_LOCKED(10020, "client device locked."),

    CONVERT_DICTITEM_LENGTH_MUST_SAME(10021, "值与字段长度不一致无法转换"),

    USER_PERMISSION_NOT_FOUND(10022, "当前用户没有此权限"),

    USERNAME_HAS_ALREADY_EXISTED(10023, "用户名已存在"),

    ROLENAME_HAS_ALREADY_EXISTED(10024, "角色名称已存在"),

    USER_UNDER_THIS_ROLE(10025, "该角色存在用户"),

    ORGANIZATION_NAME_HAS_ALREADY_EXISTED(10026, "机构名称已存在"),

    NODE_EXISTS_A_CHILD_NODE(10027, "该组织存在子节点,请先删除子节点"),
    MOBILE_HAS_ALREADY_EXISTED(10028, "手机号已存在"),
    ;

    Integer code;
    String reason;
    String description;

    ResponseStatus(Integer code) {
        this.code = code;
        this.reason = StringUtils.EMPTY;
    }

    ResponseStatus(Integer code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    ResponseStatus(Integer code, String reason, String description) {
        this.code = code;
        this.reason = reason;
        this.description = description;
    }

    public static ResponseStatus getResponseStatus(int status) {
        for (ResponseStatus responseStatus : ResponseStatus.values()) {
            if (responseStatus.getCode() == status) {
                return responseStatus;
            }
        }
        return null;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String getMessage() {

        return reason;
    }

}
