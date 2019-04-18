package com.arthur.juc.chapter8;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by xusheng on 2019/4/2.
 */
public class BankWaterService implements Runnable {

    private final CyclicBarrier c = new CyclicBarrier(4, this);

    private ExecutorService threadPool = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

    public void count() {
        for (int i = 0; i < 4; i++) {
            threadPool.execute(() -> {
                sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }

    @Override
    public void run() {
        int result = 0;
        for (Map.Entry<String, Integer> e : sheetBankWaterCount.entrySet()) {
            result += e.getValue();
        }
        sheetBankWaterCount.put("result", result);
        System.out.println(result);
    }

    public static void main(String[] args) {
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.count();
    }
}
