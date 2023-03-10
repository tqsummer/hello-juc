package com.study.hello.juc.basic.rwlock;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "hello")
public class TestShift {
    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
    public static void main(String[] args) {
        log.info(""+SHARED_UNIT+", max count : "+MAX_COUNT);
    }
}
