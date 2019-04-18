package com.arthur.juc.chapter4.threadPool;

import java.util.concurrent.TimeUnit;

/**
 * Created by xusheng on 2019/3/29.
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new DefaultThreadPool();
        for (int i = 0; i < 20; i++) {
            pool.execute(new SoutThread(i));
        }

        TimeUnit.SECONDS.sleep(5);
        pool.shutdown();
        pool.removeWorker(5);

        System.out.println("stop");

    }

    static class SoutThread implements Runnable {
        private int i;

        public SoutThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println("hello " + i);
        }
    }
}
