package com.ss.utopia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "airplane")
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;

    @ManyToOne
    @JoinColumn(name="type_id")
    private AirplaneType airplaneType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "airplane")
    @JsonIgnore
    private List<Flight> flights = new ArrayList<>();

    public Airplane() {}
    public Airplane(AirplaneType airplaneType) {
        this.airplaneType = airplaneType;
    }
    public Airplane(Integer airplaneID) {
        this.id = airplaneID;
    }

    public Integer getId() {
        return id;
    }

    public Airplane(int i, AirplaneType airplaneType) {
        this.id = i;
        this.airplaneType = airplaneType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AirplaneType getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(AirplaneType airplaneType) {
        this.airplaneType = airplaneType;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}

