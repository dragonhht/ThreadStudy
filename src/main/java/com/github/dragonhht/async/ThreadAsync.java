package com.github.dragonhht.async;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射调用方法.
 * User: huang
 * Date: 18-6-7
 */
public class ThreadAsync implements Runnable {

    private Object obj;
    private Method method;
    private Object[] args;

    public ThreadAsync(Object obj, Method method, Object[] args) {
        this.obj = obj;
        this.method = method;
        this.args = args;
    }

    @Override
    public void run() {
        try {
            method.invoke(this.obj, this.args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
