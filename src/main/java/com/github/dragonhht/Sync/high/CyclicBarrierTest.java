package com.github.dragonhht.Sync.high;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-3-7
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        final int rows = 10000;
        final int numbers = 1000;
        final int search = 5;
        final int participants = 5;
        final int linesParticipants = 2000;

        MatriMock mock = new MatriMock(rows, numbers, search);
        Results results = new Results(rows);
        Grouper grouper = new Grouper(results);
        // 等待 5 个线程运行结束，然后执行Grouper线程对象
        CyclicBarrier barrier = new CyclicBarrier(participants, grouper);

        Search searchs[] = new Search[participants];
        for (int i = 0; i < participants; i++) {
            searchs[i] = new Search(i * linesParticipants, (i * linesParticipants) + linesParticipants,
                    mock, results, 5, barrier);
            new Thread(searchs[i]).start();
        }
        System.out.println("main finished");
    }

}

class Grouper implements Runnable {

    private Results results;

    public Grouper(Results results) {
        this.results = results;
    }

    @Override
    public void run() {
        int finalResult = 0;
        System.out.println("processing...");
        int data[] = results.getData();
        for (int number : data) {
            finalResult += number;
        }
        System.out.println("total is " + finalResult);
    }
}

class Search implements Runnable {

    private int firstRow, lastRow;
    private int number;
    private MatriMock matriMock;
    private Results results;
    private CyclicBarrier cyclicBarrier;

    public Search(int firstRow, int lastRow, MatriMock matriMock, Results results, int number, CyclicBarrier cyclicBarrier) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.number = number;
        this.matriMock = matriMock;
        this.results = results;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        int counter;
        System.out.println(Thread.currentThread().getName() + ", firstRow: " + this.firstRow + ", lastRow: " + this.lastRow);
        for (int i = firstRow; i < lastRow; i++) {
            int row[] = matriMock.getRow(i);
            counter = 0;
            for (int j = 0; j < row.length; j++) {
                if (row[j] == number) {
                    counter++;
                }
            }
            results.setData(i, counter);
        }
        System.out.println(Thread.currentThread().getName() + " end");

        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

class MatriMock{
    private int data[][];

    public MatriMock(int size, int length, int number) {
        int count = 0;
        data = new int[size][length];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt(10);
                if (data[i][j] == number) {
                    count++;
                }
            }
        }

        System.out.println(count + " : " + number);
    }


    public int[] getRow(int row) {
        if ((row >= 0) && (row < data.length)) {
            return data[row];
        }
        return null;
    }
}

class Results{
    private int data[];

    public Results(int size) {
        this.data = new int[size];
    }

    public void setData(int position, int value) {
        data[position] = value;
    }

    public int[] getData() {
        return data;
    }
}
