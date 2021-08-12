package com.example.demo.object_boundary.tourist;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class Location {
    private String name;

    public static Location at(String name) {
        return new Location(name);
    }

    private Location(String name) {
        this.name = name;
    }

    protected Location() {
    }

    public String getName() {
        return name;
    }
}
