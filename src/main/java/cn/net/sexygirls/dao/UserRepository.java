package cn.net.sexygirls.dao;

import cn.net.sexygirls.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author zule
 * @description
 * @date 2019/7/14
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    List<User> findByName(String name);

    List<User> findByNameAndStatus(String name,int status);

    List<User> findByNameLike(String keyword);

    List<User> findByStatusIn(Collection<Integer> status);

    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE name = :name1  OR name = :name2 ")
    List<User> findByTwoName(@Param("name1") String name1, @Param("name2") String name2);


    @Query(nativeQuery = true, value = "SELECT u.* FROM `user` u ,role_user ru,role r WHERE u.id = ru.user_id AND ru.role_id = r.id AND r.`name` = :roleName")
    List<User> findUsersByRole(@Param("roleName")String roleName);
}
