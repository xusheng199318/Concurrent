package com.arthur.chapter1;

import org.junit.Test;

/**
 * Created by xusheng on 2018/8/27.
 */
public class ConcurrentTest {

    public static final long count = 1000000L;

    @Test
    public void testConcurrent() throws InterruptedException {
        concurrent();
        serial();
    }


    private void concurrent() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            int a = 0;
            for (long i = 0; i < count; i++) {
                a += 5;
            }
        });
        thread1.start();

        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }

        thread1.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("currency:" + time + "ms, b= " + b);
    }

    private void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }

        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }

        long time = System.currentTimeMillis() - start;

        System.out.println("serial:" + time + "ms, b =" + b);
    }
}
