package com.study.hello.juc.basic.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "hello")
public class TestCachedThreadPool {
    static ExecutorService executorService = Executors.newCachedThreadPool();
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i <1000 ; i++) {
            final  int fi =i;
            executorService.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("t"+fi+",poolsize:"+ ((ThreadPoolExecutor)executorService).getPoolSize());
            });
        }

        TimeUnit.SECONDS.sleep(61);
        log.info("poolsize:"+((ThreadPoolExecutor)executorService).getPoolSize());
    }
}
