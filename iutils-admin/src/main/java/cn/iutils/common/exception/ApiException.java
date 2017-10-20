package cn.iutils.common.exception;

import cn.iutils.common.config.Constant;

/**
 * ApiException 统一处理
 */
public class ApiException extends Exception {

    //异常代码
    private String code;

    //异常信息
    private String message;

    private ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
        this.code = Constant.FAILURE_CODE;
        this.message = message;
    }

    public ApiException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ApiException(String code, String message, Throwable cause) {
        super(code, cause);
        this.code = code;
        this.message = message;
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}