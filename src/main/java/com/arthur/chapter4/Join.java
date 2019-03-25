package com.arthur.chapter4;

/**
 * Created by xusheng on 2019/3/25.
 */
public class Join {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Domino(mainThread), String.valueOf(i));
            t.start();
        }
        System.out.println("Main Thread Terminate ");
    }

    static class Domino implements Runnable {
        private Thread preThread;

        public Domino(Thread preThread) {
            this.preThread = preThread;
        }

        @Override
        public void run() {
            try {
                preThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate ");

        }
    }
}
