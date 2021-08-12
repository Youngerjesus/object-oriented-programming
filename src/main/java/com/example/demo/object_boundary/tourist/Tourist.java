package com.example.demo.object_boundary.tourist;

import javax.persistence.*;

@Entity
public class Tourist {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "CITY"))
    private Location city;
}
