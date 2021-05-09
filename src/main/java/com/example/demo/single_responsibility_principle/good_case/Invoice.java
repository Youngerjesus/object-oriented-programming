package com.example.demo.single_responsibility_principle.good_case;

import com.example.demo.single_responsibility_principle.good_case.Book;

public class Invoice {
    Book book;
    int quantity;
    double discountRate;
    double taxRate;
    double total;

    public Invoice(Book book, int quantity, double discountRate, double taxRate, double total) {
        this.book = book;
        this.quantity = quantity;
        this.discountRate = discountRate;
        this.taxRate = taxRate;
        this.total = total;
    }

    public double calculateTotal(){
        double price = (book.price - book.price * discountRate) * this.quantity;

        double priceWithTaxes = price * (1 + taxRate);

        return priceWithTaxes;
    }
}
