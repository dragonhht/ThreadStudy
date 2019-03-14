package com.github.dragonhht.executor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-3-14
 */
public class ExecutorThirdTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Callable<String>> tasks = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            tasks.add(() -> {
                System.out.println(Thread.currentThread().getName() + ": start");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + ": end");
                return Thread.currentThread().getName();
            });
        }
        try {
            // 第一个完成的任务名
            String result = executor.invokeAny(tasks);
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
