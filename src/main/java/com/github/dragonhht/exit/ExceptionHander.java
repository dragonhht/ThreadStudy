package com.github.dragonhht.exit;

/**
 * Description.
 * User: huang
 * Date: 18-6-7
 */
public class ExceptionHander implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(e.getMessage());
    }
}
