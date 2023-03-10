package com.study.hello.juc.basic.debug;

/**
 * @Author: fangxiangqian
 * @Date: 2023/2/14
 */
public abstract class AbstractAQS {

    private volatile int state;

    protected final int getState() {
        return state;
    }

    protected final void setState(int newState) {
        state = newState;
    }

    public void doSignal() {
        System.out.println("doSignal : state = " + state);
    }

    public ASQCondition newCondition() {
        return new AQSConditionObject();
    }

    public class AQSConditionObject implements ASQCondition {

        @Override
        public void await() {
            doSignal();
        }
    }
}
