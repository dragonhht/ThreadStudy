package com.github.dragonhht;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock同步锁.
 * User: huang
 * Date: 2017/12/23
 */
public class LockTest {
    public static void main(String[] args) {
        TestLock testLock = new TestLock();

        new Thread(testLock).start();
        new Thread(testLock).start();
        new Thread(testLock).start();
        new Thread(testLock).start();

    }
}

class TestLock implements Runnable {
    private int i = 1000;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {

        // 上锁
        lock.lock();

        while (i > 0) {
            System.out.println( --i);
        }

        // 释放锁
        lock.unlock();
    }
}
