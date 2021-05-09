package com.example.demo.single_responsibility_principle.bad_case;

public class Book {
    String name;
    String authorName;
    int year;
    int price;
    String isBn;

    public Book(String name, String authorName, int year, int price, String isBn) {
        this.name = name;
        this.authorName = authorName;
        this.year = year;
        this.price = price;
        this.isBn = isBn;
    }
}
