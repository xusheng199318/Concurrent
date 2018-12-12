package com.arthur.juc.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by xusheng on 2018/12/11.
 */
public class AtomicIntegerTest {

    @Test
    public void testAtomicInteger() throws InterruptedException {
        final AtomicInteger ai = new AtomicInteger(10);
        //与预期值相同再更新值
        assertTrue(ai.compareAndSet(10, 11));
        assertEquals(ai.get(), 11);

        ai.set(0);
        //原子加1
        assertEquals(ai.incrementAndGet(), 1);
        //加1,返回结果为原先的值
        assertEquals(ai.getAndAdd(1), 1);
        //获取原先的值,重新设置新值
        assertEquals(ai.getAndSet(10), 2);

        //并发修改ai的值
        final int threadSize = 10;
        ai.set(0);
        Thread[] threads = new Thread[threadSize];
        for (int i = 0; i < threadSize; i++) {
            threads[i] = new Thread(() -> System.out.println(ai.incrementAndGet()));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(ai.get(), 10);
    }
}
