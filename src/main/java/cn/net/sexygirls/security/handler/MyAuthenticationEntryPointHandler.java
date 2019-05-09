package cn.net.sexygirls.security.handler;

import cn.net.sexygirls.utils.common.ResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 认证失败处理类
 *               在访问一个受保护的资源，用户没有通过登录认证
 * @Author: zule
 * @Date: 2019/5/7
 */
@Component
public class MyAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        StringBuffer msg = new StringBuffer("请求访问: ");
        msg.append(httpServletRequest.getRequestURI()).append(" 接口，因为认证失败，无法访问系统资源.");
        System.out.println(msg.toString());

    }
}
