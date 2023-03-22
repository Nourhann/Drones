package com.musala.task.drones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table
public class Medication {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    @Pattern(regexp="^[a-zA-Z0-9_-]*$",message="Invalid Medication Name")
    private String name;

    @Column
    private double weight;

    @Column
    @Pattern(regexp="^[A-Z0-9_]*$",message="Invalid Medication Name")
    private String code;

    @ManyToOne
    @JoinColumn(name="drone_id", nullable=false)
    private Drone drone;
    @Lob
    @Column
    private byte[] image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
