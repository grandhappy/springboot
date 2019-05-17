package cn.net.sexygirls.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: Swager配置类
 * @Author: zule
 * @Date: 2019/5/10
 */
//注解开启 swagger2 功能
@EnableSwagger2
//注解标示,这是一个配置类,@Configuation注解包含了@Component注解
//可以不用在使用@Component注解标记这是个bean了,
@Configuration
public class Swagger2Config {
    //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;
    /**
     * @Description:通过 createRestApi函数来构建一个DocketBean。函数名可以随意命名
     * @Return: springfox.documentation.spring.web.plugins.Docket
     * @Author: zule
     * @Date: 2019/5/10
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                .apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled).select()
                //扫描包路径
                .apis(RequestHandlerSelectors.basePackage("cn.net.sexygirls.controller"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build().pathMapping("/");
    }
    /**构建api文档的详细信息函数*/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("SpringBoot教程学习Restful Api")
                //创建人
                .contact("grandhappy")
                //版本号
                .version("1.0")
                //描述
                .description("Springboot集成Swagger插架，实现在线接口调试、测试，并生成在线和离线接口文档")
                .build();
    }
}