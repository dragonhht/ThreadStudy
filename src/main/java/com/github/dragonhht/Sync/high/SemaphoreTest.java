package com.github.dragonhht.Sync.high;

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

    public SemaphoreTest() {
        // 此处传入的参数为1，则该信号量为二进制信号量，即信号量的计数器的值只有0和1
        this.semaphore = new Semaphore(1);
    }

    /**
     * 添加
     * @param d
     */
    public void print(Double d) {
        try {
            // 获取信号量
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() +": 打印 start");
            System.out.println(d);
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() +": 打印 end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放信号量
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        SemaphoreTest test = new SemaphoreTest();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> test.print(Math.random())).start();
        }
    }

}
