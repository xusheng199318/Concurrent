package com.arthur.chapter4.conn;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by xusheng on 2019/3/28.
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    /**
     * 初始线程池大小
     * @param initialSize
     */
    public ConnectionPool(int initialSize) {
        for (int i = 0; i < initialSize; i++) {
            pool.addLast(ConnectionDriver.createConnection());
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                //通知其他消费者已归还一个连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 在mills内无法获取到连接则返回null
     * @param mills
     * @return
     * @throws InterruptedException
     */
    public Connection fetchConnection(int mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            }

            long future = System.currentTimeMillis() + mills;
            long remaining = mills;
            while (pool.isEmpty() && remaining > 0) {
                pool.wait();
                remaining = future - System.currentTimeMillis();
            }
            Connection result = null;
            if (!pool.isEmpty()) {
                result = pool.removeFirst();
            }

            return result;
        }
    }



}
