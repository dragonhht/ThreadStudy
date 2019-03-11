package com.github.dragonhht.Sync.high;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-3-11
 */
public class PhaserTest {

    public static void main(String[] args) {
        Random random = new Random();
        int len = 20, size = 20;
        // 创建Phaser,有20个参与线程
        Phaser phaser = new Phaser(len);
        int[][] data = new int[len][size];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < size; j++) {
                data[i][j] = random.nextInt(2);
            }
        }

        for (int i = 0; i < len; i++) {
            final int index = i;
            new Thread(() -> {
                // 让所有线程在都创建完成后运行
                System.out.println(Thread.currentThread().getName() + ": 准备第一阶段");
                phaser.arriveAndAwaitAdvance();
                if (data[index][0] == 0) {
                    System.out.println(Thread.currentThread().getName() + " : 当前线程已结束");
                    phaser.arriveAndDeregister();
                    return;
                } else {
                    System.out.println(Thread.currentThread().getName() + " : 已完成第二阶段");
                    phaser.arriveAndAwaitAdvance();
                }
                System.out.println(Thread.currentThread().getName() + ": 开始第三阶段");
                System.out.println(Thread.currentThread().getName() + ": " + Arrays.toString(data[index]));
            }).start();
        }
    }
}
