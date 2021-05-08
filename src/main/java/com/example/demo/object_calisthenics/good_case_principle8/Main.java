package com.example.demo.object_calisthenics.good_case_principle8;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Orders orders = new Orders(List.of(
                new Order("pizza", 3000),
                new Order("chicken", 4000),
                new Order("moo", 1000)));

        int totalPrice = orders.totalPrice();
        orders.printTotal();
    }
}
