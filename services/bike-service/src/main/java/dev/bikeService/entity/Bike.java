package dev.bikeService.entity;

import javax.persistence.*;
import java.util.UUID;
/**
 * Entity representing a bike.
 */
@Entity
@Table(name = "bikes")
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isParked() {
        return parked;
    }

    public void setParked(boolean parked) {
        this.parked = parked;
    }

    public BikeParkingStation getStation() {
        return station;
    }

    public void setStation(BikeParkingStation station) {
        this.station = station;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
