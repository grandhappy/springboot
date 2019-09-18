package cn.net.sexygirls.controller.monogo;

import cn.net.sexygirls.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zule
 * @description
 * @date 2019/9/18
 */
@RestController
@RequestMapping(value = "/mongo")
public class MonogoController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @ResponseBody
    @GetMapping
    public User get() {
        User user= new User();
        user.setId(111);
        user.setName("test");
        user.setPassword("123456");
        mongoTemplate.save(user);
        Query query = new Query(Criteria.where("name").is("test"));
        User user1 = mongoTemplate.findOne(query,User.class);
        return user1;
    }

}
