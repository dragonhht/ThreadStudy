# Java创建线程有四种方式
-   继承`Thread`类
-   实现`Runnable`接口
-   实现`Callable`接口
-   通过线程池获取

# [闭锁 （CountDownLatch）](./src/CountDownLatchTest.java)

> 在完成操作时，只有其他所有线程的操作都全部完成，当前操作才会继续执行
> 实例化`CountDownLatch latch = new CountDownLatch(count);
> `当中`count`为线程数,
> 当某个线程结束后，让CountDownLatch对象中的持有的Count减一`latch.countDown();`

# [使用Callable接口创建线程](./src/CallableTest.java)

> 使用Callable创建线程，只需类实现`Callable`接口
> 使用时需`FutureTask`类接收结果，
> 获取结果时，需等待线程运行完成，因此FutureTask可用于闭锁

# [Lock同步锁](./src/LockTest.java)

> 和synchronized同步代码块类似，不过需主动显示释放

# [读写锁（ReadWriteLock）](./src/ReadWriteLockTest.java)

> 读写、写写需要互斥，读读不需互斥

# Fork/Join框架

## 1.创建`Fork/Join`线程池

-   创建线程执行的任务

    ```java
    public class Task extends RecursiveAction {
        private static final long serialVersionUID = 3437482786855580091L;
    
        private List<Integer> products;
        private int first;
        private int last;
    
        public Task(List<Integer> products, int first, int last) {
            this.products = products;
            this.first = first;
            this.last = last;
        }
    
        @Override
        protected void compute() {
    
            if (last - first > 10) {
                int middel = ( last + first ) / 2;
                Task task1 = new Task(products, first, middel + 1);
                Task task2 = new Task(products, middel + 1, last);
                invokeAll(task1, task2);
            } else {
                System.out.println("compute end: " + (last - first));
            }
        }
    }
    ```

> 创建一个新的类，对于任务中有返回值的场景，类继承`RecursiveTask`,对于没有返回值的则继承`RecursiveAction`类，并重写`compute()`方法,在方法中再将任务分给子任务，如`Task task1 = new Task(products, first, middel + 1);
Task task2 = new Task(products, middel + 1, last);
invokeAll(task1, task2);`,其中使用`invokeAll`方法来调用主任务所创建的子任务。

-   创建线程池并执行任务

```java
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
```

> 使用`ForkJoinPool`创建线程池，并调用`execute()`方法讲任务提交至线程池，调用`join()`将任务合并，这样主线程将等待所有子任务完成。