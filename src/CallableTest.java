import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用Callable创建线程.
 * User: huang
 * Date: 2017/12/23
 */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallTest test = new CallTest();

        FutureTask<Integer> task = new FutureTask<Integer>(test);

        // 启动线程
        new Thread(task).start();

        // 获取结果，这过程中需等待线程运行完成
        int sum = task.get();
        System.out.println("获取： " + sum);
    }

}

class CallTest implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i < 100; i++) {
            sum++;
            System.out.println(sum);
        }
        return sum;
    }
}


