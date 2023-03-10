package com.study.hello.juc.basic.interrupt;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "hello")
public class TestInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            //1.interrupt后，sleep立即响应打断
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            //2.
            while (true){
                if(Thread.currentThread().isInterrupted()){
                    Thread.interrupted();
                    log.info("reset  && finish");
                    log.info("currentThread interrupt flag : "+Thread.currentThread().isInterrupted());
                    break;
                }
            }

        });
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        t1.interrupt();

        //sleep,wait,join线程打断后，会自动清除打断标记。以下打印为false（原因是打断标记已被清除）
        //除以上三种情况，属于正常线程，以下打印都为true，
        log.info("t1 interrupt flag : "+t1.isInterrupted());
    }

}
