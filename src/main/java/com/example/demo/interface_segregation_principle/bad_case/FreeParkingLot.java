package com.example.demo.interface_segregation_principle.bad_case;

public class FreeParkingLot implements  ParkingLot{
    @Override
    public void parkCar() {

    }

    @Override
    public void unparkCar() {

    }

    @Override
    public void getCapacity() {

    }

    @Override
    public double calculateFee(Car car) {
        return 0;
    }

    @Override
    public void doPayment(Car car) {
        throw new RuntimeException("Parking lot is free");
    }
}
