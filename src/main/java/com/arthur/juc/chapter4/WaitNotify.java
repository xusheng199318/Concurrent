package com.arthur.juc.chapter4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "Wait Thread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "Notify Thread");
        notifyThread.start();
    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "Begin To Run");
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " flag is true, wait @ " +
                                new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " flag is false. running @ " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " hold lock. notify @ " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));

                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }

            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " hold lock again. Sleep @ "+
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
    }
}
