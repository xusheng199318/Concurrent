package com.arthur.juc.chapter4.conn;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xusheng on 2019/3/28.
 */
public class ConnectionPoolTest {
    //连接池初始化10个连接
    private static ConnectionPool pool = new ConnectionPool(10);
    private static CountDownLatch start = new CountDownLatch(1);
    private static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        int threadCount = 30;
        end = new CountDownLatch(threadCount);
        int execCount = 20;

        for (int i = 0; i < threadCount; i++) {
            Thread t = new Thread(new ConnectionRunner(execCount, got, notGot),
                    "Connection Thread" + i + "Runner Start");
            t.start();
        }

        start.countDown();
        end.await();
        System.out.println("total invoke: " + (threadCount * execCount));
        System.out.println("got connection: " + got);
        System.out.println("notGot connection: " + notGot);
    }

    static class ConnectionRunner implements Runnable {

        //一个线程执行count次数据库操作
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try {
                //保证所有线程一起执行
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (count > 0) {
                try {
                    Connection connection = pool.fetchConnection(1000);
                    if (connection == null) {
                        notGot.incrementAndGet();
                        continue;
                    }

                    try {
                        connection.createStatement();
                        connection.commit();
                    } finally {
                        pool.releaseConnection(connection);
                        got.incrementAndGet();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
