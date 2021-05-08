package com.example.demo.object_calisthenics.good_case_principle2;

public class Payment {
    boolean isDead;
    boolean isSeparated;
    boolean isRetired;

    public Payment(boolean isDead, boolean isSeparated, boolean isRetired) {
        this.isDead = isDead;
        this.isSeparated = isSeparated;
        this.isRetired = isRetired;
    }

    public double getPayAmount(){
        if(isDead){
            return deadAmount();
        }
        if(isSeparated){
            return separatedAmount();
        }
        if(isRetired){
            return retiredAmount();
        }
        return normalPayAmount();
    }

    private double deadAmount() {
        return 100;
    }

    private double separatedAmount() {
        return 50;
    }

    private double retiredAmount() {
        return 60;
    }

    private double normalPayAmount() {
        return 30;
    }
}
