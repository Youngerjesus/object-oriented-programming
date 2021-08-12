package com.example.demo.object_boundary.tourpackage;

import com.example.demo.object_boundary.tour.Tour;
import com.example.demo.object_boundary.tourist.Location;
import com.example.demo.object_boundary.tourist.Tourist;
import com.example.demo.object_boundary.tourmap.TourMap;
import com.example.demo.object_boundary.tourplanner.TourPlanner;

import javax.persistence.*;

@Entity
public class TourPackage {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int rank;

    private int stockCount;

    private int plannedCount;

    private int confirmedCount;

    @ManyToOne
    @JoinColumn(name = "TOUR_MAP_ID")
    private TourMap tourMap;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TOUR_PLANNER_ID")
    private TourPlanner tourPlanner;

    public Tour plan(Tourist tourist, Location from, Location to) {
        if (stockCount <= 0) {
            throw new RuntimeException("여행 상품 재고가 부족해서 예약을 완료할 수 없습니다.");
        }

        return tourPlanner.plan(tourist, from, to);
    }

    public void increasePlannedCount() {
        plannedCount++;
    }

    public void confirm(Tour tour) {
        if (stockCount <= 0) {
            throw new RuntimeException("여행 상품 재고가 부족해서 예약을 완료할 수 없습니다.");
        }

        stockCount--;
        plannedCount--;
        confirmedCount++;
    }
}
