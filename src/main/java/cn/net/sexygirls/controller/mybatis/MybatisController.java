package cn.net.sexygirls.controller.mybatis;

import cn.net.sexygirls.dao.mapper.UserMapper;
import cn.net.sexygirls.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zule
 * @description
 * @date 2019/8/26
 */
@Controller
@RequestMapping(value = "/mybatis/user")
public class MybatisController {
    @Autowired
    UserMapper userMapper;

    @ResponseBody
    @GetMapping
    public User save(String name, String password) {
        User user = userMapper.selectById(1);
        return user;
    }
}
