package com.github.dragonhht.Sync.read.writer;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁示例.
 *
 * @author: huang
 * @Date: 2019-3-2
 */
public class ReadAndWriter {

    private int price1;
    private int price2;

    ReadWriteLock readWriteLock;

    public ReadAndWriter(int price1, int price2) {
        this.price1 = price1;
        this.price2 = price2;
        this.readWriteLock = new ReentrantReadWriteLock();
    }

    /**
     * 获取数据(使用读操作锁)
     * @return
     */
    public int getPrice1() {
        readWriteLock.readLock().lock();
        int price = this.price1;
        readWriteLock.readLock().unlock();
        return price;
    }

    /**
     * 获取数据(使用读操作锁)
     * @return
     */
    public int getPrice2() {
        readWriteLock.readLock().lock();
        int price = this.price2;
        readWriteLock.readLock().unlock();
        return price;
    }

    /**
     * 设置数据(使用写操作锁)
     * @param price1
     * @param price2
     */
    public void setPrices(int price1, int price2) {
        readWriteLock.writeLock().lock();
        this.price1 = price1;
        this.price2 = price2;
        readWriteLock.writeLock().unlock();
    }

    public static void main(String[] args) {
        ReadAndWriter readAndWriter = new ReadAndWriter(0, 1);
        Random random = new Random();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("writer");
                readAndWriter.setPrices(random.nextInt(10000), random.nextInt(10000));
                System.out.println("modified");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName() + " : " + readAndWriter.getPrice1());
                    System.out.println(Thread.currentThread().getName() + " : " + readAndWriter.getPrice2());
                }
            }).start();
        }
    }
}
