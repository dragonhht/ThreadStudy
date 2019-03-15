package com.github.dragonhht.fork.join;

import java.util.LinkedList;
import java.util.List;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-3-15
 */
public class ProductStorege {

    public List<Product> createProduct(int size) {
        List<Product> list = new LinkedList<>();
        for (int i = 9; i < size; i++) {
            Product product = new Product();
            product.setName("product: " + i);
            product.setPrice(3);
            list.add(product);
        }
        return list;
    }



}
