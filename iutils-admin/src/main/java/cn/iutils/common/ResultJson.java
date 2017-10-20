package cn.iutils.common;

import cn.iutils.common.config.Constant;

/**
 * rest接口返回数据JSON
 * <p>
 * HTTP 400 - 请求无效
 * HTTP 401 - 未授权：登录失败
 * HTTP 403 - 禁止访问
 * HTTP 404 - 无法找到文件
 * HTTP 405 - 资源被禁止
 * HTTP 406 - 无法接受
 * HTTP 407 - 要求代理身份验证
 * HTTP 410 - 永远不可用
 * HTTP 412 - 先决条件失败
 * HTTP 414 - 请求 - URI 太长
 * HTTP 500 - 内部服务器错误
 * <p>
 */
public class ResultJson {

    //异常代码
    private String code;

    //异常信息
    private String message;

    //结果数据
    private Object results;

    private ResultJson() {
    }

    public ResultJson(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultJson(String code, String message, Object results) {
        this.code = code;
        this.message = message;
        this.results = results;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResults() {
        return results;
    }

    public void setResults(Object results) {
        this.results = results;
    }

    public static ResultJson successJson(Object resultDate) {
        ResultJson result = new ResultJson();
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MSG);
        result.setResults(resultDate);
        return result;
    }

    public static Object successJson(String message, Object resultDate) {
        ResultJson result = new ResultJson();
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(message);
        result.setResults(resultDate);
        return result;
    }

    public static ResultJson failureJson(String code, Object resultDate) {
        ResultJson result = new ResultJson();
        result.setCode(code != null && code != null ? code : Constant.FAILURE_CODE);
        result.setMessage(Constant.FAILURE_MSG);
        result.setResults(resultDate);
        return result;
    }

    public static Object failureJson(String code, String message, Object resultDate) {
        ResultJson result = new ResultJson();
        result.setCode(code != null && code != null ? code : Constant.FAILURE_CODE);
        result.setMessage(message);
        result.setResults(resultDate);
        return result;
    }

    public static ResultJson exceptionJson() {
        ResultJson result = new ResultJson();
        result.setCode(Constant.ERROR_CODE);
        result.setMessage(Constant.ERROR_MSG);
        result.setResults(Constant.ERROR);
        return result;
    }

    @Override
    public String toString() {
        return "ResultJson{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", results=" + results +
                '}';
    }

}
