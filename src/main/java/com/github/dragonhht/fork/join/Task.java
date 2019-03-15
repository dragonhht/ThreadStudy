package com.github.dragonhht.fork.join;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-3-15
 */
@AllArgsConstructor
public class Task extends RecursiveAction {
    private static final long serialVersionUID = 1L;

    private List<Product> products;
    private int first;
    private int last;
    private double increment;

    @Override
    protected void compute() {
        if (last - first < 10) {
            updatePrice();
        } else {
            int middle = (last - first) / 2;
            System.out.println("创建子任务");
            Task task1 = new Task(products, first, middle, increment);
            Task task2 = new Task(products, middle + 1, last, increment);
            invokeAll(task1, task2);
        }
    }

    private void updatePrice() {
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() + (i + increment));
        }
    }
}
