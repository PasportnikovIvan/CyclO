package dev.warehouseService.dto;

import dev.warehouseService.entity.BikePart;

import java.util.List;

public class WarehouseDTO {

    private Long id;
    private List<BikePart> parts;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BikePart> getParts() {
        return parts;
    }

    public void setParts(List<BikePart> parts) {
        this.parts = parts;
    }
}

