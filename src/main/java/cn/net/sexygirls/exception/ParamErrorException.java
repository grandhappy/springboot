package cn.net.sexygirls.exception;

/**
 * @author zule
 * @description 参数错误异常
 * @date 2019/5/14
 */
public class ParamErrorException extends BaseException{
    public ParamErrorException() {
    }

    public ParamErrorException(String message) {
        super(message);
    }

    public ParamErrorException(Integer code, String message) {
        super(code, message);
    }
}
