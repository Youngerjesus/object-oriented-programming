package com.example.demo.object_calisthenics.good_case_principle4;

import java.util.List;

public class Wallet {
    List<Money> moneyList;

    public int getTotalMoney() {
       int totalMoney = 0;

       for(Money money : moneyList){
           totalMoney += money.get();
       }

       return totalMoney;
    }
}
