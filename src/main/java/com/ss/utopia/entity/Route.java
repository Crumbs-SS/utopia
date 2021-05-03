package com.ss.utopia.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    @NotNull
    @Valid
    private Airport oriAirport;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    @NotNull
    @Valid
    private Airport desAirport;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "route")
    @JsonIgnore
    private List<Flight> flights = new ArrayList<>();

    public Route(){

    }
    public Route(Integer routeId){
        this.id = routeId;
    }

    public Route(Airport oriAirport, Airport desAirport) {
        this.oriAirport = oriAirport;
        this.desAirport = desAirport;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void setOriAirport(Airport oriAirport) {
        this.oriAirport = oriAirport;
    }

    public void setDesAirport(Airport desAirport) {
        this.desAirport = desAirport;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Airport getOriAirport() {
        return oriAirport;
    }


    public Airport getDesAirport() {
        return desAirport;
    }
}
