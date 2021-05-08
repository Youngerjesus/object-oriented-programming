package com.example.demo.object_calisthenics.good_case_principle8;

public class Name {
    String firstName;
    String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String get(){
        return firstName + " " + lastName;
    }
}
