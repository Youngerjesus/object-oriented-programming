package com.example.demo.object_calisthenics.bad_case_principle4;

public class Person {
    Wallet wallet;

    public Person(Wallet wallet) {
        this.wallet = wallet;
    }

    public int getMoney(){
        return wallet.getTotalMoney().get();
    }
}
