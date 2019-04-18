package com.arthur.juc.chapter1;

public class DeadLock {
    private static String A = "A";
    private static String B = "B";


    public static void main(String[] args) {
        new DeadLock().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(() -> {
            System.out.println("t1");
            synchronized (A) {
                try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("1");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("t2");
            synchronized (B) {
                synchronized (A) {
                    System.out.println("2");
                }
            }


        });

        t1.start();
        t2.start();
    }
}
