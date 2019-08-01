package cn.net.sexygirls.controller.jpa;

import cn.net.sexygirls.dao.UserRepository;
import cn.net.sexygirls.entity.User;
import cn.net.sexygirls.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zule
 * @description
 * @date 2019/7/14
 */
@Controller
@RequestMapping(value = "/jpa/user")
public class JpaController {
    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @PostMapping
    public User save(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setStatus(1);
        userRepository.save(user);
        return user;
    }

    @ResponseBody
    @DeleteMapping
    public String delete(int id) {
        User user = userRepository.getOne(id);
        userRepository.delete(user);
        return "success";
    }

    @ResponseBody
    @PutMapping
    public User update(int id, String name) {
        User user = userRepository.getOne(id);
        user.setName(name);
        userRepository.save(user);
        return user;
    }

    @ResponseBody
    @GetMapping
    public void get() {
        List<User> userList = userRepository.findByName("hbase");
        System.out.println("the result findByName(\"hbase\") is:" + JSON.toJSONString(userList));
        userList = userRepository.findByNameAndStatus("hbase", 1);
        System.out.println("the result findByNameAndStatus(\"hbase\",1) is:" + JSON.toJSONString(userList));
        userList = userRepository.findByNameLike("%sql%");
        System.out.println("the result findByNameLike(\"%sql%\") is:" + JSON.toJSONString(userList));
        List<Integer> statusList = new ArrayList<>();
        statusList.add(1);
        statusList.add(2);
        userList = userRepository.findByStatusIn(statusList);
        System.out.println("the result findByStatusIn(statusList) is:" + JSON.toJSONString(userList));
    }

    @ResponseBody
    @GetMapping("/native")
    public void nativeget() {
        List<User> userList = userRepository.findByTwoName("hbase", "mysql");
        System.out.println("the result findByTwoName(\"hbase\",\"mysql\") is:" + JSON.toJSONString(userList));
    }

    @ResponseBody
    @GetMapping("/join1")
    public void joinGet1() {
        List<User> userList = userRepository.findUsersByRole("admin");
        System.out.println("the result findUsersByRole(\"admin\") is:" + JSON.toJSONString(userList));
    }

    @ResponseBody
    @GetMapping("/join2")
    public void joinGet2() {
        List<User> userList = userService.joinQuery("admin",1);
        System.out.println("the result joinQuery(\"admin\",1) is:" + JSON.toJSONString(userList));
    }

}
