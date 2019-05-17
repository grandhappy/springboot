package cn.net.sexygirls.exception;

/**
 * @author zule
 * @description 未登录异常
 * @date 2019/5/14
 */
public class UnauthorizedException extends BaseException{
    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(Integer code, String message) {
        super(code, message);
    }
}
