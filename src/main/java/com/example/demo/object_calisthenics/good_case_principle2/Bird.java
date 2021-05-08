package com.example.demo.object_calisthenics.good_case_principle2;

public abstract class Bird {
    int numberOfCounts;

    public Bird(int numberOfCounts) {
        this.numberOfCounts = numberOfCounts;
    }

    public double getBaseSpeed(){
        return 50;
    }
    abstract double getSpeed();
}

class European extends Bird{
    public European(int numberOfCounts) {
        super(numberOfCounts);
    }

    @Override
    double getSpeed() {
        return getBaseSpeed();
    }
}

class African extends Bird{
    public African(int numberOfCounts) {
        super(numberOfCounts);
    }

    @Override
    double getSpeed() {
        return getBaseSpeed() - getLoadFactor() * numberOfCounts;
    }

    private double getLoadFactor() {
        return 2;
    }
}

class NorwegianBlue extends Bird{
    private boolean isNailed;

    public NorwegianBlue(int numberOfCounts, boolean isNailed) {
        super(numberOfCounts);
        this.isNailed = isNailed;
    }

    @Override
    double getSpeed() {
        return isNailed ? 0 : getBaseSpeed();
    }
}

