package com.example.demo.liskov_substitution_principle.good_case;

public class Square {
    protected int width, height;

    public Square() {}

    public Square(int size) {
        width = height = size;
    }

    public void setWidth(int width) {
        this.width = width;
        this.height = width;
    }

    public void setHeight(int height) {
        this.height = height;
        this.width = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getArea() {
        return width * height;
    }
}
