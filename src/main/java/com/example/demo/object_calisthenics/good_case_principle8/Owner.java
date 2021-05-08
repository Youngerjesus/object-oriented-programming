package com.example.demo.object_calisthenics.good_case_principle8;

public class Owner {
    Name name;
    Age age;

    public Owner(Name name, Age age) {
        this.name = name;
        this.age = age;
    }

    public Name getName() {
        return name;
    }

    public Age getAge() {
        return age;
    }
}
