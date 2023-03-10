package com.study.hello.juc.basic.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "hello")
public class TestMyLock {
    static MyLock myLock = new MyLock();
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            try {
                myLock.lock();
                log.info("t1 get lock");
                TimeUnit.SECONDS.sleep(10);
                log.info("t1 finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                myLock.unlock();
            }

        });

        Thread t2 = new Thread(()->{
            try {
                myLock.lock();
                log.info("t2 get lock");
                TimeUnit.SECONDS.sleep(20);
                log.info("t2 finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                myLock.unlock();
            }

        });

        t1.start();
        t2.start();
    }
}
