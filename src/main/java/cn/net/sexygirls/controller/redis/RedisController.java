package cn.net.sexygirls.controller.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zule
 * @description
 * @date 2019/9/17
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @ResponseBody
    @GetMapping
    public String get() {
        return stringRedisTemplate.opsForValue().get("news");
    }
}
