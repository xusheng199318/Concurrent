package com.arthur.juc.chapter5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xusheng on 2019/4/2.
 */
public class ReentrantLockTest {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(new CountUtil(lock));
        Thread t2 = new Thread(new CountUtil(lock));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }

    static class CountUtil implements Runnable{

        private final Lock lock;

        public CountUtil(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            for (int i = 0; i < 100; i++) {
                count++;
            }
            lock.unlock();
        }
    }
}
