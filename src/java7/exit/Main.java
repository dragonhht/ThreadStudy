package java7.exit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description.
 *
 * @author: huang
 * Date: 18-5-31
 */
public class Main {

    public static void countDownLatchTest() {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new PrintThread(latch)).start();
        }
    }

    public static void forkTest() throws InterruptedException {

        // 生成需要的数据
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int temp = random.nextInt(10000);
            list.add(temp);
        }

        Task task = new Task(list, 0, list.size());
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(task);
        // 使用join方法讲子任务的任务合并，这样主线程讲等待子任务完成
        task.join();

        forkJoinPool.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        forkTest();
    }

}
