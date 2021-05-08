package com.example.demo.object_calisthenics.good_case_principle3;

public class Order {
    int totalAmount;

    public Order(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int CalculateMoney(Money money){
        return money.get() - totalAmount;
    }

    private void validMoney(int money) {

    }
}
