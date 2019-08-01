package cn.net.sexygirls.controller.swagger;

import cn.net.sexygirls.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Description: swagger接口类
 * 生成在线接口文档，常用的注解示例包括@ApiOperation,@ApiImplicitParams,@ApiIgnore等等
 * @Author: zule
 * @Date: 2019/5/10
 */
@Api(tags="user api")
@RestController
public class SwaggerController {
    @ApiOperation(value = "用户详情",notes="注意事项：敏感不返回")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "id",value = "用户id",required = true,paramType = "path")
    })
    @PostMapping(value = "/swagger/{id}")
    public User getUser(@PathVariable String id){
        User user  = new User(1,"grandhappy","123456",1);
        return user;
    }

    /**使用该注解忽略这个API */
    @ApiIgnore
    @RequestMapping(value = "/swagger/ignore", method = RequestMethod.GET)
    public String  ignore() {
        return "ignore";
    }
}
