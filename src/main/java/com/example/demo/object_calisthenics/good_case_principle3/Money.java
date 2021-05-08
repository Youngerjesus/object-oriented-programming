package com.example.demo.object_calisthenics.good_case_principle3;

public class Money {
    int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        // do something()
    }

    public int get(){
        return money;
    }
}
