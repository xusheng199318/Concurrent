package com.arthur.juc.lock;

import org.junit.Test;

/**
 * Created by xusheng on 2018/12/12.
 */
public class UnlockTest implements Runnable{

    private int value = 0;

    @Override
    public void run() {
        final int addCount = 100;
        for (int i = 0; i < addCount; i++) {
            value++;
        }
    }

    @Test
    public void testUnlock() throws InterruptedException {
        UnlockTest unlockTest = new UnlockTest();
        final int loopCount = 10;
        //启动10个线程每个线程对value进行累加100次操作
        for (int i = 0; i < loopCount; i++) {
            Thread thread = new Thread(unlockTest);
            thread.start();
        }

        Thread.sleep(2000);
        System.out.println(unlockTest.value);
    }
}
