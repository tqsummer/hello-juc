package com.study.hello.juc.basic.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "hello")
public class TestCountDownLatch {
    public static void main(String[] args) {

        //倒计计数器，countdown一次，count-1，当count=0时，开始执行。可重复拦。
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        //倒计计数器，countdown一次，count-1，当count=0时，开始执行。只会拦一次。
        //CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i <3 ; i++) {
            new Thread(()->{
                try {
                    //countDownLatch.countDown();
                    cyclicBarrier.await();
                    log.info("Thread : {} countdown",Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(1);
                    log.info("Thread : {} end",Thread.currentThread().getName());
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"t"+i).start();
        }

    }
}
