# [闭锁 （CountDownLatch）](./src/CountDownLatchTest.java)

> 在完成操作时，只有其他所有线程的操作都全部完成，当前操作才会继续执行
> 实例化`CountDownLatch latch = new CountDownLatch(count);
> `当中`count`为线程数,
> 当某个线程结束后，让CountDownLatch对象中的持有的Count减一`latch.countDown();`