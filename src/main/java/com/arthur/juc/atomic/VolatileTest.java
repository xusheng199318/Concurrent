package com.arthur.juc.atomic;

import org.junit.Test;

/**
 * Created by xusheng on 2018/12/11.
 */
public class VolatileTest implements Runnable{
    private volatile boolean done = true;
    private int value = 0;


    @Override
    public void run() {
        while (done) {
            value++;
        }
    }

    @Test
    public void testVolatile() throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();
        Thread thread = new Thread(volatileTest);
        thread.start();
        Thread.sleep(1000);
        System.out.println(volatileTest.value);
        volatileTest.done = false;
        System.out.println("done");
        Thread.sleep(1000);
        System.out.println(volatileTest.value);
    }
}
