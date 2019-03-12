package com.github.dragonhht.sync.synchronize;

import com.github.dragonhht.sync.User;

/**
 * Synchronized.
 *
 * @author: huang
 * @Date: 2019-2-28
 */
public class SynchronizedTest {

    /**
     * 不能同时访问同一对象中被synchronized修饰的方法
     */
    public static void testStatic() {
        User user = new User();
        new Thread(() -> {
            try {
                user.addIndex(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                user.minusIndex(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public static void main(String[] args) {
        testStatic();
    }

}
