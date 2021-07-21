package com.example.demo.multiple_if_else;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String loginType = scanner.next();

        LoginPrinter.valueOf(loginType.toUpperCase()).print();
    }
}
