package cn.net.sexygirls;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: zule
 * @Date: 2019/5/5
 */
@SpringBootApplication
@MapperScan("cn.net.sexygirls.dao.mapper")
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
