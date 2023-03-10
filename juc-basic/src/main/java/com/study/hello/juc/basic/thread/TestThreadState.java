package com.study.hello.juc.basic.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "hello")
public class TestThreadState {
    public static void main(String[] args) {
        int state =0;
        Object lock =new Object();
        List<Thread> threadList = new ArrayList<>();
        CountDownLatch cdl = new CountDownLatch(3);
        CountDownLatch notifyCdl = new CountDownLatch(4);

        Thread thread1 = new Thread(()->{
            //
            cdl.countDown();
            try {
                TimeUnit.NANOSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock){
                for (Thread thread:threadList) {
                    log.info("t1 get lock , Thread : {}, state : {}",thread.getName(),thread.getState());
                }
                try {
                    notifyCdl.countDown();
                    lock.wait();
                    for (Thread thread:threadList) {
                        log.info("t1 after wait , Thread : {}, state : {}",thread.getName(),thread.getState());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");

        Thread thread2 = new Thread(()->{
            //
            cdl.countDown();
            synchronized (lock){
                for (Thread thread:threadList) {
                    log.info("t2 get lock , Thread : {}, state : {}",thread.getName(),thread.getState());
                }
                try {
                    notifyCdl.countDown();
                    lock.wait();
                    for (Thread thread:threadList) {
                        log.info("t2 after wait , Thread : {}, state : {}",thread.getName(),thread.getState());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"t2");

        Thread thread3 = new Thread(()->{
            //
            cdl.countDown();
            synchronized (lock){
                for (Thread thread:threadList) {
                    log.info("t3 get lock , Thread : {}, state : {}",thread.getName(),thread.getState());
                }
                try {
                    notifyCdl.countDown();
                    lock.wait(100000);
                    for (Thread thread:threadList) {
                        log.info("t3 after wait , Thread : {}, state : {}",thread.getName(),thread.getState());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t3");



        threadList.add(thread1);
        threadList.add(thread2);
        threadList.add(thread3);
        for (Thread thread:threadList) {
            log.info("before start , Thread : {}, state : {}",thread.getName(),thread.getState());
            thread.start();
            log.info("after start , Thread : {}, state : {}",thread.getName(),thread.getState());
        }

        new Thread(()->{
            //
            notifyCdl.countDown();
            synchronized (lock){
                for (Thread thread:threadList) {
                    log.info("t4 before notify all , Thread : {}, state : {}",thread.getName(),thread.getState());
                }
                lock.notifyAll();
                for (Thread thread:threadList) {
                    log.info("t4 after notify all , Thread : {}, state : {}",thread.getName(),thread.getState());
                }
            }
        },"t4").start();


    }
}
