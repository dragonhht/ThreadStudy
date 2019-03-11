package com.github.dragonhht.Sync.high;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-3-8
 */
public class CyclicBarrierStudy {

    private int[][] storage;

    public CyclicBarrierStudy(int size, int length, int number) {
        // 初始化二维数组数据，并记录所要查找的数字出现的次数
        this.storage = new int[size][length];
        Random random = new Random();
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                storage[i][j] = random.nextInt(150);
                if (storage[i][j] == number) {
                    count++;
                }
            }
        }
        // 打印正确结果信息，用于后面的校验
        System.out.printf("需查找的数值 %d 共出现 %d 次发\n", number, count);
    }

    public int[] getData(int index) {
        return this.storage[index];
    }

    public static void main(String[] args) {
        final int size = 10, length = 50, number = 100;
        int[] countData = new int[size];
        CyclicBarrierStudy study = new CyclicBarrierStudy(size, length, number);

        // 创建CyclicBarrier对象，并指定等待 size个线程结束，结束后运行另一个线程计算总数
        CyclicBarrier cyclicBarrier = new CyclicBarrier(size, () -> {
            // 计算总数
            int count = 0;
            for (int n : countData) {
                count += n;
            }
            System.out.printf("查找结束，数值 %d 共出现 %d 次\n", number, count);
        });

        // 用线程计算每组的出现的数量
        for (int i = 0; i < size; i++) {
            final int index = i;
            new Thread(() -> {
                // 将二维数组分组进行查询
                int[] data = study.getData(index);
                int count = 0;
                for (int n : data) {
                    if (n == number) {
                        count++;
                    }
                }
                // 保存该组数组中出现指定数据的次数
                countData[index] = count;
                System.out.println(Thread.currentThread().getName() + " search end");
                try {
                    // 等待其他线程完成
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
