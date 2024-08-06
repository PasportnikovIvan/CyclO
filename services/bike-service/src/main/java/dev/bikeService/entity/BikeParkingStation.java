package dev.bikeService.entity;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;
/**
 * Entity representing a bike parking station.
 */
@Entity
@Table(name = "bike_parking_stations")
public class BikeParkingStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String locationAddress;

    @Column(nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "station")
    private List<Bike> bikes;
}
