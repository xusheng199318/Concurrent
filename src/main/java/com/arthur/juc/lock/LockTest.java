package com.arthur.juc.lock;

import org.junit.Test;


/**
 * Created by xusheng on 2018/12/12.
 */
public class LockTest {

    @Test
    public void testLock() throws InterruptedException {
        final int maxThread = 10;
        final int loopCount = 100;
        final AtomicIntegerWithLock aiwl = new AtomicIntegerWithLock(0);
        for (int i = 0; i < maxThread; i++) {
            new Thread(() -> {
                for (int j = 0; j < loopCount; j++) {
                    System.out.println(aiwl.getAndIncrement());
                }
            }).start();
        }

        Thread.sleep(2000);
        System.out.println(aiwl.get());
    }
}
