package com.example.demo.object_calisthenics.bad_case_principle4;

import java.util.List;

public class Wallet {
    List<Money> moneyList;

    public Wallet(List<Money> moneyList) {
        this.moneyList = moneyList;
    }

    public Money getTotalMoney() {
        int totalMoney = 0;
        for (Money money : moneyList){
            totalMoney += money.get();
        }
        return new Money(totalMoney);
    }
}
