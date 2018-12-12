package com.arthur.juc.lock;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xusheng on 2018/12/12.
 */
public class AtomicIntegerWithLock {
    private int value;
    private Lock lock = new ReentrantLock();

    public AtomicIntegerWithLock(int value) {
        this.value = value;
    }

    public AtomicIntegerWithLock() {super();}

    public final int get() {
        lock.lock();
        try {
            return this.value;
        } finally {
            lock.unlock();
        }
    }

    public final void set(int newValue) {
        lock.lock();
        try {
            this.value = newValue;
        } finally {
            lock.unlock();
        }
    }

    public final int getAndIncrement() {
        lock.lock();
        try {
            return this.value++;
        } finally {
            lock.unlock();
        }
    }


}
