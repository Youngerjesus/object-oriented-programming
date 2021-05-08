package com.example.demo.object_calisthenics.bad_case_principle8;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("pizza", 3000));
        orders.add(new Order("chicken", 4000));
        orders.add(new Order("moo", 1000));

        Integer total = orders.stream()
                .map(order -> order.getPrice())
                .reduce(0, Integer::sum);
    }
}
