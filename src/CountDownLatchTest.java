import java.util.concurrent.CountDownLatch;

/**
 * 闭锁.
 * User: huang
 * Date: 2017/12/23
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new Test(latch)).start();
        }

        try {
            // 等待闭锁结束
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("_______end_________");
    }

}

class Test  implements Runnable {

    private CountDownLatch latch;

    public Test(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println("test: " + i);
            }
            // 闭锁中减一
            latch.countDown();
        }

    }
}
