package com.study.hello.juc.basic.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j(topic = "hello")
public class TestRWLock1 {
    public static void main(String[] args) {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        Lock r = rwLock.readLock();
        Lock w = rwLock.writeLock();
        Thread t1 = new Thread(()->{
            int count = 10;
            try {
                for(int i =0;i<count;i++){
                    r.lock();
                    log.info("get r lock : " +i);
                }
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                for(int i =0;i<count;i++){
                    r.unlock();
                    log.info("r unlock : " +i);
                }
            }

        });

        t1.start();
    }
}
