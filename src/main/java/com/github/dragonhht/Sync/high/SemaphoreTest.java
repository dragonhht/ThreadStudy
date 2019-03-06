package com.github.dragonhht.Sync.high;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * 信号量.
 *
 * @author: huang
 * @Date: 2019-3-4
 */
public class SemaphoreTest {

    /** 声明一个信号量对象. */
    private Semaphore semaphore;
    /** 存储数据. */
    private LinkedList<Double> storage = new LinkedList<>();
    /** 最大数量. */
    private int maxSize = 20;

    public SemaphoreTest() {
        // 此处传入的参数为1，则该信号量为二进制信号量，即信号量的计数器的值只有0和1
        // 第二个参数表示是否公平
        this.semaphore = new Semaphore(1, true);
    }

    /**
     * 添加
     * @param d
     */
    public void add(Double d) {
        try {
            // 获取信号量
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() +": add start");
            if (this.storage.size() < this.maxSize) {
                this.storage.add(d);
            }
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() +": add end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放信号量
            semaphore.release();
        }
    }

    /**
     * 获取
     */
    public Double get() {
        try {
            // 获取信号量
            semaphore.acquire();
            double d = 0.0;
            System.out.println(Thread.currentThread().getName() +": get start");
            if (this.storage.size() > 0 ) {
                d = this.storage.poll();
            }
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() +": get end");
            return d;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0.0;
        } finally {
            // 释放信号量
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        SemaphoreTest test = new SemaphoreTest();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    test.add(Math.random());
                }
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    System.out.println(test.get());
                }
            }).start();
        }
    }

}
