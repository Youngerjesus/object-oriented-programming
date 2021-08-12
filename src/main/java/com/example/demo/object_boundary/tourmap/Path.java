package com.example.demo.object_boundary.tourmap;

import com.example.demo.object_boundary.tourist.Location;

import javax.persistence.*;

@Embeddable
@Access(AccessType.FIELD)
public class Path {

    @AttributeOverride(name = "name", column = @Column(name = "DEPATRURE"))
    private Location from;

    @AttributeOverride(name = "name", column = @Column(name = "DESTINATION"))
    private Location to;

    private double distance;
}
