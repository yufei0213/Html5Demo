package io.ubt.app.model;

/**
 * Created by wangyufei on 16/10/27.
 *
 *  0 为正常
 *  1 开头为系统错误
 *  2 开头为业务异常
 */

public enum Status {

    // 正常
    SUCCESS(0, "success"),

    // 系统错误
    SYSTEM_ERROR(10001, "System error"), // 系统错误
    API_NOT_FOUND(10002, "Request api not found."), // 未找到请求的接口
    ILLEGAL_REQUEST(10003, "Illegal request."), // 非法请求
    AUTHENTICATION_FAIL(10004, "Authentication fail"), // 认证失败
    API_NOT_IMPLEMENT(10005, "Api not implement."), // 接口暂未实现

    // 通用业务异常，错误码范围 20001-20010
    PARSE_PARAMETERS_ERROR(20001, "Parse parameters error."), // 参数处理失败
    VALIDATE_PARAMETERS_ERROR(20002, "Validate parameters error."), // 参数验证失败

    // 登录相关错误码，错误码范围 20101 -20199
    ACCOUNT_NOT_EXIST_ERROR(20101, "Account does not exist."), // 账号不存在
    INVALID_PASSWORD_ERROR(20102, "Invalid password."), // 密码错误

    REQUEST_TIMEOUT(-2, "Request Timeout."),
    PARSE_RESULTS_ERROR(-3, "Parse result error."),

    // 未知异常
    UNKNOWN_ERROR(-1, "Unknown error");

    int code;
    String msg;

    Status(int code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    public int getCode() {

        return code;
    }

    public String getMsg() {

        return msg;
    }

    public static Status getStatusByCode(int code) {

        for (Status status : Status.values()) {

            if (status.getCode() == code) {

                return status;
            }
        }
        return null;
    }
}
