package com.example.demo.interface_segregation_principle.good_case;

public interface PaidParkingLot extends ParkingLot{
    double calculateFee(Car car); // Returns the price based on number of hours
    void doPayment(Car car);
}

class Car {

}
