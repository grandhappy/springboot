package cn.net.sexygirls.controller.concurrent;

import cn.net.sexygirls.service.concurrent.Task1;
import cn.net.sexygirls.service.concurrent.Task2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/concurrent")
public class ConcurrentController {
    @Autowired
    Task1 c1;
    @Autowired
    Task2 c2;
    @RequestMapping("/index")
    String index() throws InterruptedException {
        Map<String, Future<Integer>> map = new HashMap();
        map.put("c1",c1.exe());
        map.put("c2",c2.exe());


        for (String executable_plat : map.keySet()) {
            Future<Integer> f = map.get(executable_plat);
            try {
                //System.out.println(f.get());
                System.out.println(f.get(5000, TimeUnit.MILLISECONDS));
            } catch (Exception e) {
                f.cancel(true);
                e.printStackTrace();
            }
        }
        System.out.println("main run");
        return "ok";
    }

}
