package com.example.demo.object_boundary.tourplanner;

import com.example.demo.object_boundary.tour.Tour;
import com.example.demo.object_boundary.tour.TourState;
import com.example.demo.object_boundary.tourist.Location;
import com.example.demo.object_boundary.tourist.Tourist;
import com.example.demo.object_boundary.tourmap.Path;
import com.example.demo.object_boundary.tourpackage.TourPackage;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PLANNER_TYPE")
public abstract class TourPlanner {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "tourPlanner")
    private TourPackage tourPackage;

    public Tour plan(Tourist tourist, Location from, Location to) {
        List<Path> route = createRoute();
        Tour tour = new Tour(tourist, tourPackage, route, TourState.PLANNED);
        validatePlannedTour(tour);
        tourPackage.increasePlannedCount();
        return tour;
    }

    protected List<Path> createRoute() {
        return new ArrayList<>();
    }


    protected void validatePlannedTour(Tour tour) {

    }
}
