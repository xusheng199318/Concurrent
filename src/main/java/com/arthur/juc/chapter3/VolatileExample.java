package com.arthur.juc.chapter3;

import org.junit.Test;

/**
 * 内存可见性
 */
public class VolatileExample extends Thread{

    private int value = 0;
    private boolean stop = false;

    @Override
    public void run() {
        while (!stop) {
            value++;
        }
    }

    @Test
    public void testVolatile() throws InterruptedException {
        VolatileExample thread = new VolatileExample();
        thread.start();
        Thread.sleep(2000);
        thread.stop = true;
        System.out.println(thread.value);
        Thread.sleep(2000);
        System.out.println(thread.value);
    }
}
