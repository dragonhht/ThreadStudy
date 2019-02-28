package com.github.dragonhht.async;

/**
 * Description.
 * User: huang
 * Date: 18-6-7
 */
public class AsyncTest {

    @Async(size = 3)
    public void test() {
        for (int i = 0; i < 3; i++) {
            System.out.println("测试: " + i);
        }
    }



}
