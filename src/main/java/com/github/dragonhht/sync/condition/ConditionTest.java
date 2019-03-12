package com.github.dragonhht.sync.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition.
 *
 * @author: huang
 * @Date: 2019-3-4
 */
public class ConditionTest {

    private static class Storage {
        private LinkedList<Double> storage = new LinkedList<>();
        private int maxSize;
        // 创建Lock
        private Lock lock = new ReentrantLock();
        private Condition producer = lock.newCondition();
        private Condition consumer = lock.newCondition();

        public Storage(int maxSize) {
            this.maxSize = maxSize;
        }

        public Double get() {
            Double n = 0.0;
            lock.lock();
            try {
                while (this.storage.size() < 1) {
                    System.out.println("等待生产");
                    consumer.await();
                }
                System.out.println(Thread.currentThread().getName() + " consumer" );
                n = this.storage.poll();
                producer.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return n;
        }

        public void add(Double n) {
            lock.lock();
            try {
                while (this.storage.size() >= this.maxSize) {
                    System.out.println("等待消费");
                    producer.await();
                }
                System.out.println(Thread.currentThread().getName() + " producer" );
                this.storage.add(n);
                consumer.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        Storage storage = new Storage(20);
            new Thread(() -> {
                while (true) {
                    storage.add(Math.random());
                }
            }).start();
            for (int i = 0; i < 5; i++) {
                new Thread(() -> {
                    while (true) {
                        System.out.println(storage.get());
                    }
                }).start();
            }
    }

}
