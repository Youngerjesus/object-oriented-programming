package com.example.demo.object_calisthenics.good_case_principle2;

public class MyStatus {

    String status(int hour, boolean isStudy){
        if(hour > 4 && hour <= 12){
            return "sleep";
        }

        return isStudy ? "study" : "vacation";
    }
}
