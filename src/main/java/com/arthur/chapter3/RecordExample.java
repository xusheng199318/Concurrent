package com.arthur.chapter3;

import org.junit.Test;

/**
 * Created by xusheng on 2018/9/25.
 */
public class RecordExample {
    int a = 0;
    boolean flag = false;

    /**
     * A线程执行
     */
    public void writer() throws InterruptedException {
        a = 1;                  // 1
        flag = true;            // 2
    }

    /**
     * B线程执行
     */
    public void read(){
        if(flag){                  // 3
            int i = a + a;         // 4
            System.out.println(i);
        } else {
            System.out.println("flag false");
        }
    }

    @Test
    public void testRecordExample() throws InterruptedException {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        threadA.start();
        threadB.start();
        Thread.sleep(5000);
    }

    class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                writer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class ThreadB extends Thread {
        @Override
        public void run() {
            read();

        }
    }
}


