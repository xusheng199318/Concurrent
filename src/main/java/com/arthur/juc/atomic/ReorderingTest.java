package com.arthur.juc.atomic;

import org.junit.Test;

/**
 * Created by xusheng on 2018/12/11.
 */
public class ReorderingTest {

    private int x = 0, y = 0, a = 0, b = 0;

    @Test
    public void testReorder() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
            });
            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("x : " + x + " y : " + y);
        }

    }
}
