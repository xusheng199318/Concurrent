package com.arthur.juc.lock;

import org.junit.Test;

/**
 * Created by xusheng on 2018/12/12.
 */
public class MyThreadLocal implements Runnable{
   private ThreadLocal<String> threadLocal = new ThreadLocal<>();
    @Override
    public void run() {
        threadLocal.set(Thread.currentThread().getId() + "");
        System.out.println(threadLocal.get());
    }

    @Test
    public void testThreadLocal() throws InterruptedException {
        MyThreadLocal myThreadLocal = new MyThreadLocal();
        for (int i = 0; i < 10; i++) {
            new Thread(myThreadLocal).start();
        }
        Thread.sleep(2000);
    }
}
