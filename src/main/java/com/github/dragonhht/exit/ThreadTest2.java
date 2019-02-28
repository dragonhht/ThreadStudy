package com.github.dragonhht.exit;

/**
 * Description.
 * User: huang
 * Date: 18-6-7
 */
public class ThreadTest2 implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("------------");
        }

        /*try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
//        System.out.println("线程二结束");
    }
}
