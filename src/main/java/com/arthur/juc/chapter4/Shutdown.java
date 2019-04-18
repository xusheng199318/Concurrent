package com.arthur.juc.chapter4;

import java.util.concurrent.TimeUnit;

/**
 * Created by xusheng on 2019/3/22.
 */
public class Shutdown {

    public static void main(String[] args) throws InterruptedException {
        Runner runner1 = new Runner();
        Thread countThread = new Thread(runner1, "Runner1");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();

        Runner runner2 = new Runner();
        Thread two = new Thread(runner2, "Runner2");
        two.start();
        TimeUnit.SECONDS.sleep(1);
        runner2.cancel();

    }

    static class Runner implements Runnable {

        private int i;

        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println(Thread.currentThread().getName() + " is " + i);
        }

        public void cancel() {
            on = false;
        }
    }
}
