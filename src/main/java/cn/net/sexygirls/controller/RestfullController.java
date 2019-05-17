package cn.net.sexygirls.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:RestFull Interface
 * @Author: zule
 * @Date: 2019/5/5
 */

@RestController
public class RestfullController {
    @RequestMapping("/index")
    String index(){
        return "hello new world";
    }
}
