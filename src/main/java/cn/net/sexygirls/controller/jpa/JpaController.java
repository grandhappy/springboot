package cn.net.sexygirls.controller.jpa;

import cn.net.sexygirls.dao.UserRepository;
import cn.net.sexygirls.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author zule
 * @description
 * @date 2019/7/14
 */
@Controller
@RequestMapping(value = "/jpa/user")
public class JpaController {

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @PostMapping
    public User save(String name,String password){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setStatus(1);
        userRepository.save(user);
        return user;
    }
    @ResponseBody
    @DeleteMapping
    public String delete(int id){
        User user = userRepository.getOne(id);
        userRepository.delete(user);
        return "success";
    }
    @ResponseBody
    @PutMapping
    public User update(int id,String name){
        User user = userRepository.getOne(id);
        user.setName(name);
        userRepository.save(user);
        return user;
    }

}
