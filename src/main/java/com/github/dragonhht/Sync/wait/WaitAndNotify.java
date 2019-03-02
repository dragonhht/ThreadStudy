package com.github.dragonhht.Sync.wait;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用wait()、notify()和notifyAll()实现同步.
 *
 * @author: huang
 * @Date: 2019-2-28
 */
public class WaitAndNotify {

    /**
     * 模拟存储的队列.
     */
    @Data
    private class Storage {
        private int maxSize;
        private LinkedList<Integer> data;

        public Storage(int maxSize) {
            this.maxSize = maxSize;
            this.data = new LinkedList<>();
        }

        /**
         * 添加数据.
         */
        public synchronized void add(Integer data) {
            while (this.data.size() == maxSize) {
                try {
                    System.out.println("队列已满");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("添加数据");
            this.data.add(data);
            notifyAll();
        }

        /**
         * 获取数据
         * @return
         */
        public synchronized Integer get() {
            while (this.data.size() == 0) {
                try {
                    System.out.println("队列已空");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Integer result = this.data.poll();
            System.out.println("获取数据: " + result);
            notifyAll();
            return result;
        }
    }

    public void run() {
        Random random = new Random();
        Storage storage = new Storage(20);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                int n = random.nextInt(100);
                storage.add(n);
            }
        });
        Thread consumer = new Thread(() -> {
           for (int i = 0; i < 100; i++) {
               storage.get();
           }
        });
        producer.start();
        consumer.start();
    }

    public static void main(String[] args) {
        WaitAndNotify test = new WaitAndNotify();
        test.run();
    }

}
