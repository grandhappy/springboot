package cn.net.sexygirls.security.handler;

import cn.net.sexygirls.utils.common.ResponseUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @Description: 登录失败处理类
 *               用户登录系统失败后需要做的业务操作
 *               包括：分类返回登录失败原因
 * @Author: zule
 * @Date: 2019/5/6
 */
@Component
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,AuthenticationException e) throws IOException, ServletException {
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            //可在此记录登录失败次数，进行锁定
            ResponseUtil.out(response, ResponseUtil.resultMap(401,"用户名或密码错误"));
        } else if (e instanceof DisabledException) {
            ResponseUtil.out(response, ResponseUtil.resultMap(401,"账户被禁用，请联系管理员"));
        //可以新增登录异常次数超限LoginFailLimitException
        } else {
            ResponseUtil.out(response, ResponseUtil.resultMap(401,"登录失败"));
        }
    }
}
