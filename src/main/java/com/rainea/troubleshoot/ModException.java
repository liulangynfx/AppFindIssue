package com.rainea.troubleshoot;

import com.google.common.collect.Lists;
import com.rainea.troubleshoot.model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author liulang
 * @date 2021-08-04
 **/
public class ModException {
    public static void main(String[] args) {
//        ExecutorService executor = ExecutorServiceUtil.getExecutorServiceByType(ExecutorServiceUtil.ExecutorServiceType.DEFAULT);
        ExecutorService executor = Executors.newFixedThreadPool(20);


        List<Product> Products = new ArrayList<>();
        Product item1 = new Product();
        item1.setId("1");
        item1.setPrice(1.0);

        Product item2 = new Product();
        item2.setId("2");
        item2.setPrice(1.0);

        Product item3 = new Product();
        item3.setId("3");
        item3.setPrice(1.0);

        Product item4 = new Product();
        item4.setId("4");
        item4.setPrice(1.0);

        Product item5 = new Product();
        item5.setId("5");
        item5.setPrice(1.0);

        Product item6 = new Product();
        item6.setId("6");
        item6.setPrice(1.0);

        Product item7 = new Product();
        item7.setId("7");
        item7.setPrice(1.0);

        Products.add(item1);
        Products.add(item2);
        Products.add(item3);
        Products.add(item4);
        Products.add(item5);
        Products.add(item6);
        Products.add(item7);


        List<List<Product>> subSets = Lists.partition(Products, 2);

        for (List<Product> items : subSets) {
            executor.submit(() -> {
                try {
                    Map<String, List<Product>> logisticsHeaderGroupByMap = items.stream().collect(Collectors.groupingBy(e -> e.getId()));
//                    items.remove(0);
                    Iterator<Product> iterator = items.iterator();
                    Product next = iterator.next();
                    iterator.remove();


                    System.out.println("11");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }

        System.out.println("--------over");
    }
}
