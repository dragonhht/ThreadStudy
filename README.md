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