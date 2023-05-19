package ua.vozniuk.AirlinesProject.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name="plane")
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name="id")
    private int id;

    @Column(name="model")
    @Size(min=3, max = 20, message = "Model is too short or too long")
    private String model;

    @Column(name="production_year")
    @Min(value = 1903, message = "Planes were invented in 1903")
    @Max(value = 2023, message = "Can not save planes from the future")
    private int productionYear;

    @Column(name="capacity")
    @Min(value = 0, message = "capacity should be at least 0")
    private int capacity;

    @OneToMany(mappedBy = "planeOfTicket")
    private List<Ticket> planeTickets;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Ticket> getPlaneTickets() {
        return planeTickets;
    }

    public void setPlaneTickets(List<Ticket> planeTickets) {
        this.planeTickets = planeTickets;
    }
}
