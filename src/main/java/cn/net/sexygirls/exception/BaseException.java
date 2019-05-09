package cn.net.sexygirls.exception;

/**
 * @Description:自定义异常父类
 * @Author: zule
 * @Date: 2019/5/7
 */
public class BaseException extends RuntimeException{
    private String errorCode;
    private String errorMsg;

    public BaseException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
