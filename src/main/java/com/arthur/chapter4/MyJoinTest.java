package com.arthur.chapter4;

/**
 * Created by xusheng on 2019/3/25.
 */
public class MyJoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            SleepUtils.second(2);
            System.out.println("test Thread");
        });
        t.start();
        t.join();
        System.out.println("main Thread");
    }
}
