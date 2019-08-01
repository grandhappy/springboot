package cn.net.sexygirls.service;

import cn.net.sexygirls.entity.User;

import java.util.List;

/**
 * @author zule
 * @description
 * @date 2019/7/31
 */
public interface UserService {
    List<User> joinQuery(String roleName, int status);
}
