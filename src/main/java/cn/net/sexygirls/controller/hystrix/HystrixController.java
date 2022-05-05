package cn.net.sexygirls.controller.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description:RestFull Interface
 * @Author: zule
 * @Date: 2022/5/5
 */
@RestController
public class HystrixController {
    @HystrixCommand(fallbackMethod = "queryResult",commandProperties
            = {
            @HystrixProperty(name
                    = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "500")
    })
    @RequestMapping("/hystrix/index")
    String index() throws InterruptedException {
        Thread.sleep(100);
        return "hello new world";
    }

    @HystrixCommand(fallbackMethod = "queryResult",commandProperties
            = {
            @HystrixProperty(name
                    = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "500")
    })
    @RequestMapping("/hystrix/index1")
    String index1() throws InterruptedException {
        Thread.sleep(1000);
        return "hello new world1";
    }

    private String queryResult() {

        return "hello hystrix world";
    }
}
