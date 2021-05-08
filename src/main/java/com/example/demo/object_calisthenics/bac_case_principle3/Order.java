package com.example.demo.object_calisthenics.bac_case_principle3;

public class Order {
    int totalAmount;

    public Order(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int CalculateMoney(int money){
        validMoney(money);
        return money - totalAmount;
    }

    private void validMoney(int money) {

    }
}
