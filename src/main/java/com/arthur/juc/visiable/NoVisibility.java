package com.arthur.juc.visiable;

import org.junit.Test;

/**
 * Created by xusheng on 2018/12/18.
 */
public class NoVisibility {

    private static boolean ready;

    private static int number;

    static class ReaderThread extends Thread {

        @Override
        public void run() {
            while (!ready) {
                System.out.println(111);
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args)  throws InterruptedException {
        new ReaderThread().start();

        number = 42;
        ready = true;
    }
}
