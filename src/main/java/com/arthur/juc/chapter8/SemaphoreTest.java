package com.arthur.juc.chapter8;

import com.arthur.juc.chapter4.SleepUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(3);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            int temp = i;
            threadPool.execute(() -> {
                try {
                    s.acquire();
                    System.out.println("save data " + temp);
                    SleepUtils.second(3);
                    s.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }
}
