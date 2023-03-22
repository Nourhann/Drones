package com.musala.task.drones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;

import java.util.List;

@Entity
@Table
public class Drone {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Model model;

    @Max(value = 500,message = "Max weight is 500gr")
    @Column
    private double weight;

    @Column
    private long batteryCapacity;

    @Column
    private State state;

    @OneToMany(mappedBy="drone")
    private List<Medication> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public long getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(long batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Medication> getItems() {
        return items;
    }

    public void setItems(List<Medication> items) {
        this.items = items;
    }
}
