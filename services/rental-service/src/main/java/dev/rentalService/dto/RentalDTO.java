package dev.rentalService.dto;

public class RentalDTO {
    private Long id;
    private Long userId;
    private Long bikePartId;
    private boolean isActive;

    // Getters and Setters

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
