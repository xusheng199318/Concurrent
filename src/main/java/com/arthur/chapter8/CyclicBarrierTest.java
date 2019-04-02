package com.arthur.chapter8;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by xusheng on 2019/4/2.
 */
public class CyclicBarrierTest {
    private static final CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                System.out.println("threadTest wait");
                c.await();
                System.out.println("threadTest run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            System.out.println("ThreadMain wait");
            c.await();
            System.out.println("ThreadMain run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
