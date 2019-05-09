package cn.net.sexygirls.security.handler;


import cn.net.sexygirls.security.config.SecurityUserDetails;
import cn.net.sexygirls.utils.common.ResponseUtil;
import cn.net.sexygirls.utils.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 登录成功处理类
 *               用户登录系统成功后，需要做的业务操作
 *               包括：1.生成token;2.将用户信息保存到redis;3.将身份存储到SecurityContext里
 * @Author: zule
 * @Date: 2019/5/6
 */
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityUserDetails securityUserDetails = (SecurityUserDetails)authentication.getPrincipal();
        String token = JwtTokenUtil.generateAccessToken(securityUserDetails);
        token = JwtTokenUtil.tokenPrefix + token;

        ResponseUtil.out(response, ResponseUtil.resultMap(200,"登录成功", token));
    }
}
