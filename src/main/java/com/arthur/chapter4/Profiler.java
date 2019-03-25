package com.arthur.chapter4;

import java.util.concurrent.TimeUnit;

/**
 * Created by xusheng on 2019/3/25.
 */
public class Profiler {

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<>();

    private static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    private static final long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Cost " + Profiler.end() + " mills ");
    }
}
