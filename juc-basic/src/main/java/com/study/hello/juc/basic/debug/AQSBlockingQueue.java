package com.study.hello.juc.basic.debug;

/**
 * @Author: fangxiangqian
 * @Date: 2023/2/14
 */
public class AQSBlockingQueue {
    private AQSReentrantLock lock;
    private ASQCondition notFull;
    private ASQCondition notEmpty;

    public AQSBlockingQueue() {
        this.lock = new AQSReentrantLock();
        this.notFull = this.lock.newCondition();
        this.notEmpty = this.lock.newCondition();
    }

    public void put(Object e){
        this.lock.lock();
        this.notEmpty.await();
    }

    public void poll(){
        this.lock.lock();
        this.notFull.await();
    }

    public static void main(String[] args) {
        AQSBlockingQueue abq = new AQSBlockingQueue();
        abq.put("a");
        abq.poll();
    }
}
