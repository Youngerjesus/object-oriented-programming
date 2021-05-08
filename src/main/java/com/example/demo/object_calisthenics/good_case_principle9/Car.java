package com.example.demo.object_calisthenics.good_case_principle9;


import com.example.demo.object_calisthenics.good_case_principle7.Owner;

public class Car {
    String name;
    Owner owner;

    public Car(String name, Owner owner) {
        this.name = name;
        this.owner = owner;
    }

    public void moveForward(){
    }

    public void status(){
        System.out.println("name: " + name + " owner: " + owner);
    }
}
