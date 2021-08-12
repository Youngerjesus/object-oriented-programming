package com.example.demo.object_boundary.tourplanner;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("LEAST_DISTANCE")
public class LeastDistancePlanner extends TourPlanner{

    private double distanceLimit;
}
