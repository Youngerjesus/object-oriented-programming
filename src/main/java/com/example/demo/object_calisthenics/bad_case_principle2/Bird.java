package com.example.demo.object_calisthenics.bad_case_principle2;

public class Bird {
    private double numberOfCounts;
    private boolean isNailed;

    public Bird(double numberOfCounts, boolean isNailed) {
        this.numberOfCounts = numberOfCounts;
        this.isNailed = isNailed;
    }

    public double getSpeed(String type){
        switch (type){
            case "EUROPEAN":
                return getBaseSpeed();
            case "AFRICAN":
                return getBaseSpeed() - getLoadFactor() * numberOfCounts;
            case "NORWEGIAN_BLUE":
                return (isNailed) ? 0 : getBaseSpeed();
        }

        throw  new RuntimeException();
    }

    private double getLoadFactor() {
        return 0;
    }

    private double getBaseSpeed() {
        return 0;
    }
}
