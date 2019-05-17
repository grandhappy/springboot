package cn.net.sexygirls.exception;

/**
 * @author zule
 * @description 禁用的异常
 * @date 2019/5/14
 */
public class ForbiddenException extends BaseException{
    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(Integer code, String message) {
        super(code, message);
    }

}
