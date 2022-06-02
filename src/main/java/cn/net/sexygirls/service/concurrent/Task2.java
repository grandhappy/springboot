package cn.net.sexygirls.service.concurrent;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class Task2 {
    @Async
    public Future exe(){
        System.out.println("task2 run");
        return new AsyncResult(2);
    }


}
