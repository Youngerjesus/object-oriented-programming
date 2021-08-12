package com.example.demo.object_boundary.tourmap;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TourMap {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    @CollectionTable(name = "TOUR_MAP_PATH", joinColumns = @JoinColumn(name = "TOUR_MAP_ID"))
    private List<Path> paths = new ArrayList<>();
}
