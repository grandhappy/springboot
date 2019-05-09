package cn.net.sexygirls.controller.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zule
 * @Date: 2019/5/7
 */
@RestController
public class SecurityController {
    //===========step1:jwt token 身份认证===========
    //登录页面
    @GetMapping("/security/loginpage")
    String loginpage(){
        return "loginpage";
    }
    //登出
    @GetMapping("/security/loginout")
    String loginout(){
        return "loginout success";
    }
    //不需要token，就可以访问
    @GetMapping("/security/noauth")
    String noauth(){
        return "noauth";
    }


    @GetMapping("/security/needauth")
    String auth(){
        return "needauth";
    }
}
