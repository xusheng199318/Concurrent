package com.arthur.juc.chapter8;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("thread1");
            c.countDown();
        }).start();

        new Thread(() -> {
            System.out.println("thread2");
            c.countDown();
        }).start();

        c.await();
        System.out.println("thread main");

    }
}
