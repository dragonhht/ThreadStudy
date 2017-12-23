import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁.
 * User: huang
 * Date: 2017/12/23
 */
public class ReadWriteLockTest {

    public static void main(String[] args) {
        TestReadWriteLock test = new TestReadWriteLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.set();
            }
        }, "写").start();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test.get();
                }
            }, "读").start();
        }
    }

}

class TestReadWriteLock {
    private int num = 0;

    // 读写锁
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void get() {
        // 读锁
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": " + num);
        }finally {
            lock.readLock().unlock();
        }

    }

    public void set() {
        // 写锁
        lock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName());
            this.num++;
        }finally {
            lock.writeLock().unlock();
        }

    }
}
