package com.github.dragonhht.executor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 获取任务结果.
 *
 * @author: huang
 * @Date: 2019-3-14
 */
public class ExecutorSecondTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Integer>> futures = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            Future<Integer> result = executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " start");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end");
                return index;
            });
            futures.add(result);
        }

        for (Future<Integer> future : futures) {
            // 是否完成
            System.out.println(future.isDone());
            if (!future.isCancelled()) {
                future.cancel(true);
            }
           /* try {
                System.out.println(future.isCancelled());
                // 获取结果
                int result = future.get();
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }*/
        }

        executor.shutdown();
    }

}
