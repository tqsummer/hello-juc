package com.study.hello.juc.basic.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "hello")
public class TestSemaphore {
    public static void main(String[] args) {
        //限流，可同时处理线程数
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i <7 ; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    log.info("Thread : {} acquire",Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(3);
                    log.info("Thread : {} end",Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                    log.info("Thread : {} ,after release , permits : {}",Thread.currentThread().getName(),semaphore.availablePermits());
                }
            },"t"+i).start();
        }
    }
}
