package com.arthur.chapter4;

public class InheritableThreadLocalTest {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static final InheritableThreadLocal<String> inheritableThreadLocal =
            new InheritableThreadLocal<>();

    static class MainThread implements Runnable {

        @Override
        public void run() {
            try {
                String value = "hello world";
                System.out.println("主线程设置ThreadLocal的值为 " + value);
                threadLocal.set(value);
                inheritableThreadLocal.set(value);

                Thread childThread = new Thread(new ChildThread(), "Child Thread");
                childThread.start();
                Thread.sleep(1000);

                value = "changed";
                System.out.println("主线程设置ThreadLocal的值为 " + value);
                threadLocal.set(value);
                inheritableThreadLocal.set(value);

                Thread.sleep(1000);
                System.out.println();
                System.out.println("在主线程获取的ThreadLocal的值：" + threadLocal.get());
                System.out.println("在主线程获取InheritableThreadLocal的值 " + inheritableThreadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ChildThread implements Runnable {

        @Override
        public void run() {
            try {
                getThreadLocal();
                Thread.sleep(1500);
                System.out.println("子线程获取重设之后的值---");
                getThreadLocal();

                System.out.println();
                String value = "i'm still a kid ";
                System.out.println("在子线程设置ThreadLocal的值为：" + value);
                threadLocal.set(value);
                inheritableThreadLocal.set(value);

                Thread.sleep(3000);
                System.out.println("子线程重新获取 " + inheritableThreadLocal.get());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void getThreadLocal() {
            System.out.println("在子线程中获取ThreadLocal的值： " + threadLocal.get());
            System.out.println("在子线程中获取InheritableThreadLocal的值： " + inheritableThreadLocal.get());
        }
    }

    public static void main(String[] args) {
        Thread mainThread = new Thread(new MainThread(), "Main Thread");
        mainThread.start();
    }
}
