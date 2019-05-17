package cn.net.sexygirls.config.exception;

import cn.net.sexygirls.exception.*;
import cn.net.sexygirls.model.Result;
import cn.net.sexygirls.utils.common.ResultEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zule
 * @description
 * @date 2019/5/14
 */
@ControllerAdvice
public class MyExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultException(HttpServletRequest request, Exception e){
        e.printStackTrace();
        return Result.builder()
                .code(ResultEnum.EXCEPTION.getCode())
                .message(ResultEnum.EXCEPTION.getMsg())
                .build();
    }

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public Result myException(HttpServletRequest request, BaseException e){
        e.printStackTrace();
        Integer code=e.getCode();
        String message=e.getMessage();
        if(e instanceof ParamErrorException){
            code = ResultEnum.PARAM.getCode();
        }else if(e instanceof UnauthorizedException) {
            code = ResultEnum.UNAUTH.getCode();
        }else if(e instanceof ForbiddenException){
            code = ResultEnum.FORBIDDEN.getCode();
        }else if(e instanceof NotExistedException){
            code = ResultEnum.NOTEXIST.getCode();
        }else {
            if (e.getCode()==null){
                code=ResultEnum.EXCEPTION.getCode();
            }
            if (e.getMessage()==null){
                message=ResultEnum.EXCEPTION.getMsg();
            }
        }

        return Result.builder()
                .code(code)
                .message(message)
                .build();
    }
}
