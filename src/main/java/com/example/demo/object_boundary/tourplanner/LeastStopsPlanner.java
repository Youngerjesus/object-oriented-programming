package com.example.demo.object_boundary.tourplanner;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("LEAST_STOPS")
public class LeastStopsPlanner extends TourPlanner {

    private int stopsLimit;
}
