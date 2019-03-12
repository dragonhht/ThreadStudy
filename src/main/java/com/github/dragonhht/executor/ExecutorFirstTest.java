package com.github.dragonhht.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-3-12
 */
public class ExecutorFirstTest {

    public static void main(String[] args) {
        // 定义执行器，创建一个缓存线程池
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        // 将任务交给执行器
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ": 任务开始执行");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": 任务结束");
            });
        }

        // 关闭执行器资源
        executor.shutdown();
    }

}
