package com.example.demo.object_calisthenics.bad_case_principle2;

public class MyStatus {

    String status(int hour, boolean isStudy){
        String status = "";

        if(hour > 4 && hour <= 12){
            status = "sleep";
        } else {
            if(isStudy){
                status = "study";
            } else {
                status = "vacation";
            }
        }

        return status;
    }
}
