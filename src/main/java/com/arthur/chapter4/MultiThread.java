package com.arthur.chapter4;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

/**
 * Created by xusheng on 2018/11/27.
 */
public class MultiThread {

    @Test
    public void testThreadsInfo() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        //Arrays.stream(threadInfos).forEach(item -> System.out.println(item.getThreadId() + " : " + item.getThreadName()));
    }
}
