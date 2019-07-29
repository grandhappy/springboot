package cn.net.sexygirls.dao;

import cn.net.sexygirls.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zule
 * @description
 * @date 2019/7/14
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
