package cn.net.sexygirls.service;

import cn.net.sexygirls.dao.UserRepository;
import cn.net.sexygirls.entity.Role;
import cn.net.sexygirls.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zule
 * @description
 * @date 2019/7/31
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> joinQuery(String roleName, int status){
        List<User> users = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();

                Join<User,Role> join = root.join("roles",JoinType.LEFT);
                list.add(criteriaBuilder.like(join.get("name").as(String.class), roleName));

                list.add(criteriaBuilder.equal(root.get("status").as(Integer.class),status));

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        });
        return users;
    }
}
