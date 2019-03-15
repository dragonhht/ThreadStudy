package com.github.dragonhht.fork.join;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-3-15
 */
public class FirstTest {

    public static void main(String[] args) {
        ProductStorege storege = new ProductStorege();
        List<Product> products = storege.createProduct(10000);
        Task task = new Task(products, 0, products.size(), 0.20);
        // 创建ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();
        // 执行任务
        pool.execute(task);
        // 关闭线程池
        pool.shutdown();

        for (Product product : products) {
            System.out.println(product.getName() + " , " + product.getPrice());
        }
    }
}
