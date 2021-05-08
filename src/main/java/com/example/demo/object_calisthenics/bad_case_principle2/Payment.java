package com.example.demo.object_calisthenics.bad_case_principle2;

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
        double result;

        if(isDead){
            result = deadAmount();
        }
        else {
            if(isSeparated){
                result = separatedAmount();
            }
            else {
                if(isRetired){
                    result = retiredAmount();
                }else{
                    result = normalPayAmount();
                }
            }
        }
        return result;
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
