package cn.net.sexygirls.exception;

/**
 * @author zule
 * @description 不存在的异常
 * @date 2019/5/14
 */
public class NotExistedException extends BaseException{
    public NotExistedException() {
    }

    public NotExistedException(String message) {
        super(message);
    }

    public NotExistedException(Integer code, String message) {
        super(code, message);
    }
}
