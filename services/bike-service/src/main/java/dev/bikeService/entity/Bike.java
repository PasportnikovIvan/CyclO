package dev.bikeService.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "bikes")
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private boolean parked;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private BikeParkingStation station;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String model;

    // Getters and Setters
}
