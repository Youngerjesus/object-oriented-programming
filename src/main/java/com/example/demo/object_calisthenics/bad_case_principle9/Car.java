package com.example.demo.object_calisthenics.bad_case_principle9;

import com.example.demo.object_calisthenics.good_case_principle7.Owner;

public class Car {
    String name;
    Owner owner;

    public Car(String name, Owner owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
