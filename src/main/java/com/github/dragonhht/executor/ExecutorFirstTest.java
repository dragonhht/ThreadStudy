package com.github.dragonhht.executor;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-3-12
 */
public class ExecutorFirstTest {

    public static void main(String[] args) {
        // 定义执行器，创建一个缓存线程池
        ExecutorService executor = Executors.newWorkStealingPool();
        // 提交任务
        executor.execute(() -> System.out.println("hello: " + new Date()));
        // 周期性执行任务时不要关闭执行器，否则不会周期性执行
        //executor.shutdown();
    }

}
