package com.study.hello.juc.basic.debug;

/**
 * @Author: fangxiangqian
 * @Date: 2023/2/14
 */
public class AQSReentrantLock {
    private Sync sync = new Sync();

    public void lock(){
        sync.setState(sync.getState()+1);
    }

    static class Sync extends AbstractAQS {
    }

    public ASQCondition newCondition() {
        return sync.newCondition();
    }
}
