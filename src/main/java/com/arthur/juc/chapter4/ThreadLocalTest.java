package com.arthur.juc.chapter4;

/**
 * Created by xusheng on 2019/5/7.
 */
public class ThreadLocalTest {
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("parent thread set value");
        threadLocal.set("parent thread");
        new Thread(new ChildThread(), "child thread1").start();
        new Thread(new ChildThread(), "child thread2").start();
        Thread.sleep(1000);
        String s = threadLocal.get();
        System.out.println("parent thread :" + s);
    }


    static class ChildThread implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println("child thread set value");
                threadLocal.set("child thread");
                Thread.sleep(1000);
                String s = threadLocal.get();
                System.out.println("child thread:" + s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
