package cn.net.sexygirls.security.config;

import cn.net.sexygirls.entity.user.Menu;
import cn.net.sexygirls.entity.user.Role;
import cn.net.sexygirls.entity.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description:获取用户权限的service
 * @Author: zule
 * @Date: 2019/5/6
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    /**定义角色*/
    public  static List<Role> ALL_ROLES = new ArrayList();
    public  static List<Role> ADMIN_ROLES = new ArrayList();
    public  static List<Role> COMMON_ROLES = new ArrayList();
    /**定义权限*/
    public  static List<Menu> ADMIN_MENUS = new ArrayList();
    public  static List<Menu> COMMON_MENUS = new ArrayList();
    static {
        //定义菜单
        Menu commonMenu1 = new Menu(1,"首页","/security/common",0);
        Menu commonMenu2 = new Menu(2,"服务","/security/service",0);
        Menu commonMenu3 = new Menu(3,"公司","/security/company",0);
        Menu adminMenu = new Menu(4,"系统管理","/security/manager",0);
        //初始化菜单
        ADMIN_MENUS.add(adminMenu);
        ADMIN_MENUS.add(commonMenu1);
        ADMIN_MENUS.add(commonMenu2);
        ADMIN_MENUS.add(commonMenu3);
        COMMON_MENUS.add(commonMenu1);
        COMMON_MENUS.add(commonMenu2);
        COMMON_MENUS.add(commonMenu3);
        //定义角色
        Role adminRole = new Role(1,"admin");
        Role commonRole = new Role(2,"common");
        adminRole.setMenus(ADMIN_MENUS);
        commonRole.setMenus(COMMON_MENUS);
        //初始化角色
        ADMIN_ROLES.add(adminRole);
        ADMIN_ROLES.add(commonRole);
        COMMON_ROLES.add(commonRole);
        ALL_ROLES.add(adminRole);
        ALL_ROLES.add(commonRole);
    }

    /**
     * @Description:加载用户信息&权限
     * @param: username 用户名
     * @Return: org.springframework.security.core.userdetails.UserDetails
     * @Author: zule
     * @Date: 2019/5/7
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        switch (username) {
            case "admin":
                user = new User(1, "admin", new BCryptPasswordEncoder().encode("123456"), 1);
                user.setRoles(ADMIN_ROLES);
                break;
            default:
                user = new User(2,"grandhappy",new BCryptPasswordEncoder().encode("123456"),1);
                user.setRoles(COMMON_ROLES);
        }
        return new SecurityUserDetails(user);
    }
}
