package com.arthur.chapter4.threadPool;

public interface ThreadPool<Job extends Runnable> {

    /**
     * 执行一个Job，Job需要实现Runnable
     * @param job
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作者线程
     * @param num
     */
    void addWorkers(int num);

    /**
     * 减少工作线程
     * @param num
     */
    void removeWorker(int num);

    /**
     * 得到正在等待执行的任务数量
     * @return
     */
    int getJobSize();
}