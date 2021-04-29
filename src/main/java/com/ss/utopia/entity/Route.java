package com.ss.utopia.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "route")
public class Route {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Airport oriAirport;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Airport desAirport;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "route")
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
