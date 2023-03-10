package com.study.hello.juc.basic.threadpool;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "hello")
public class Test {
    public static void main(String[] args) {
        testMethod();
    }

    public static void testMethod(){
        retry:
        for (int i = 0; i <100 ; i++) {
            log.info("i = "+i);
            if(i>10){
                break retry;
            }
        }

        log.info("break retry and do something");
        for (int i = 0; i <100 ; i++) {
            log.info("j = "+i);
        }

    }
}
