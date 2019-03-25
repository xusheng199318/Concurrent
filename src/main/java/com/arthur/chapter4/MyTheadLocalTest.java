package com.arthur.chapter4;

/**
 * Created by xusheng on 2019/3/25.
 */
public class MyTheadLocalTest {

    static final String str = "hello";

    static final ThreadLocal<String> mainThreadLocal = new ThreadLocal<String>(){

        @Override
        protected String initialValue() {
            return "Main Thread";
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new MyThread(), "ThreadLocal Thread");
        t.start();
        t.join();
        System.out.println(mainThreadLocal.get());
    }

    static class MyThread implements Runnable {
        ThreadLocal<String> threadLocal = new ThreadLocal<String>();

        @Override
        public void run() {
            System.out.println(str);
            System.out.println(mainThreadLocal.get());
        }
    }
}
