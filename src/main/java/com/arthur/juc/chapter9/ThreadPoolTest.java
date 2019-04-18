package com.arthur.juc.chapter9;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new NoReturnThread());
        Future<String> result = executor.submit(new ReturnThread());
        System.out.println("result : " + result.get());
        executor.shutdown();
    }

    static class NoReturnThread implements Runnable {
        @Override
        public void run() {
            System.out.println("noReturnThread Run");
        }
    }

    static class ReturnThread implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("returnThread Run");
            return "call";
        }
    }
}
