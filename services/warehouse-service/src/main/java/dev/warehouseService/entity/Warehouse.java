package dev.warehouseService.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public void addPart(BikePart part) {
        parts.add(part);
        part.setWarehouse(this);
    }

    public void removePart(BikePart part) {
        parts.remove(part);
        part.setWarehouse(null);
    }
}