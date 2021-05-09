package com.example.demo.interface_segregation_principle.good_case;

public interface ParkingLot {
    void parkCar();	// Decrease empty spot count by 1
    void unparkCar(); // Increase empty spots by 1
    void getCapacity();	// Returns car capacity
}


