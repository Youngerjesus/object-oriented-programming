package com.example.demo.multiple_if_else;

import java.util.Scanner;

public class Problem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String loginType = scanner.next();

        if ("google".equalsIgnoreCase(loginType)) {
            System.out.println("Google");
        }
        else if ("faceBook".equalsIgnoreCase(loginType)) {
            System.out.println("faceBook");
        }
        else if ("KaKao".equalsIgnoreCase(loginType)) {
            System.out.println("KaKao");
        }
        else if ("Naver".equalsIgnoreCase(loginType)) {
            System.out.println("Naver");
        }
    }
}
