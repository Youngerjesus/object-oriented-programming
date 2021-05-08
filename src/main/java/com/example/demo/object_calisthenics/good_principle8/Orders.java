package com.example.demo.object_calisthenics.good_principle8;

import java.util.List;

public class Orders {
    List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = orders;
    }

    public int totalPrice(){
        return orders.stream()
                .map(order -> order.getPrice())
                .reduce(0, Integer::sum);
    }

    public void printTotal(){
        System.out.println("총 금액: $" + totalPrice());
    }
}
