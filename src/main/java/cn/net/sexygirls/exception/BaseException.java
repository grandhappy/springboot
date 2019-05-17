package cn.net.sexygirls.exception;

/**
 * @Description:自定义异常父类
 * @Author: zule
 * @Date: 2019/5/7
 */
public class BaseException extends RuntimeException{
    private Integer code;
    private String message;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
        this.message=message;
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
