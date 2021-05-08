package com.example.demo.object_calisthenics.bad_case_principle8;

public class Order {
    String item;
    int price;

    public Order(String item, int price) {
        this.item = item;
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
