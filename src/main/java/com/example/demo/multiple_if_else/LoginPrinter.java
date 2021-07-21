package com.example.demo.multiple_if_else;

public enum LoginPrinter implements Printer{
    GOOGLE {
        @Override
        public void print() {
            System.out.println("Google");
        }
    },
    FACEBOOK {
        @Override
        public void print() {
            System.out.println("FaceBook");
        }
    },
    KAKAO {
        @Override
        public void print() {
            System.out.println("Kakao");
        }
    },
    NAVER {
        @Override
        public void print() {
            System.out.println("Naver");
        }
    }
}
