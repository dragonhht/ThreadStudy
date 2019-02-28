package com.github.dragonhht.exit;

import java.util.concurrent.CountDownLatch;

/**
 * Description.
 *
 * @author: huang
 * Date: 18-5-31
 */
public class PrintThread implements Runnable {

    private CountDownLatch latch;

    public PrintThread(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.printf("%S : start\n", Thread.currentThread().getName());
        latch.countDown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s : end\n", Thread.currentThread().getName());
    }
}
