package com.github.dragonhht.sync.high;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CountDownLatch.
 *
 * @author: huang
 * @Date: 2019-3-6
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        int size = 20;
        // 创建CountDownLatch对象
        CountDownLatch latch = new CountDownLatch(size);
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println("等待线程全部准备...");
                try {
                    try {
                        // 为了 latch.getCount() 顺序所以加锁控制
                        lock.lock();
                        // 减一操作，表示该线程以准备完成
                        latch.countDown();
                        System.out.println("还有 " + latch.getCount() + " 个线程需准备");
                    } finally {
                        lock.unlock();
                    }
                    // 等待其他线程准备
                    latch.await();
                    System.out.println(Thread.currentThread().getName() + ": 准备完成, 开始执行任务...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
