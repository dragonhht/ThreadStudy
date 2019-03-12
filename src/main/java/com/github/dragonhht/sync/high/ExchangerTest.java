package com.github.dragonhht.sync.high;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-3-11
 */
public class ExchangerTest {

    public static void main(String[] args) {
        LinkedList<Integer> producerData = new LinkedList<>();
        LinkedList<Integer> consumerData = new LinkedList<>();
        Exchanger<LinkedList<Integer>> exchanger = new Exchanger<>();
        new Thread(new Producer(producerData, exchanger)).start();
        new Thread(new Consumer(consumerData, exchanger)).start();
    }

    static class Producer implements Runnable{
        private LinkedList<Integer> data;
        private final Exchanger<LinkedList<Integer>> exchanger;

        public Producer(LinkedList<Integer> data, Exchanger<LinkedList<Integer>> exchangerData) {
            this.data = data;
            this.exchanger = exchangerData;
        }

        @Override
        public void run() {
            int index = 1;
            int size = 10;
            Random random = new Random();
            for (int i =0;i < size; i++) {
                System.out.printf("生产者第 %d 次交换\n", index);
                int n = random.nextInt();
                System.out.println("生产的数据: " + n);
                data.add(n);

                try {
                    // 与消费者交换数据
                    data = exchanger.exchange(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者现有数据数量: " + data.size());
                index++;
            }
        }
    }

    static class Consumer implements Runnable {

        private LinkedList<Integer> data;
        private final Exchanger<LinkedList<Integer>> exchanger;

        public Consumer(LinkedList<Integer> data, Exchanger<LinkedList<Integer>> exchanger) {
            this.data = data;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            int index = 1;
            int size = 10;
            for (int i =0;i < size; i++) {
                System.out.printf("消费者第 %d 次交换\n", index);
                try {
                    // 与生产者交换数据
                    data = exchanger.exchange(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费的数据: " + data.poll());
                index++;
            }
        }
    }
}
