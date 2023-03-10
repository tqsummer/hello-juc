package com.study.hello.juc.basic.thread;

public class TestThread {
    public static void main(String[] args) {

        final Thread t2 = new Thread(()->{
            Thread theThread =Thread.currentThread();
            System.out.println(theThread.getName());
        },"t2");

        final Thread t1 = new Thread(()->{
            Thread theThread =Thread.currentThread();
            System.out.println(theThread.getState());
            System.out.println(theThread.getName());
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");


        t1.start();
        t2.start();
    }
}
