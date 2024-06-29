package dev.rentalService.entity;

import javax.persistence.*;

@Entity
public class RentalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long bikePartId;
    private boolean isActive;

    // Constructors, getters, and setters
    public RentalRecord() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBikePartId() {
        return bikePartId;
    }

    public void setBikePartId(Long bikePartId) {
        this.bikePartId = bikePartId;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
