package com.example.demo.object_boundary.tour;

import com.example.demo.object_boundary.tourist.Tourist;
import com.example.demo.object_boundary.tourmap.Path;
import com.example.demo.object_boundary.tourpackage.TourPackage;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
public class Tour {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private TourState tourState;

    @ManyToOne
    @JoinColumn
    private Tourist tourist;

    @ManyToOne
    @JoinColumn
    private TourPackage tourPackage;

    @ElementCollection
    @CollectionTable(name = "TOUR_PATH", joinColumns = @JoinColumn(name = "TOUR_ID"))
    private List<Path> paths = new ArrayList<>();

    public Tour(Tourist tourist, TourPackage tourPackage, List<Path> paths, TourState tourState) {
        this.tourist = tourist;
        this.tourPackage = tourPackage;
        this.paths = paths;
        this.tourState = tourState;
    }

    public void confirm() {
        if (!tourState.equals(TourState.PLANNED)) {
            throw new RuntimeException(
                "여행이 계획 상태가 아닙니다. 계획 상태인 여행만 확정할 수 있습니다");
        }

        tourPackage.confirm(this);
        this.tourState = TourState.CONFIRMED;
    }
}
