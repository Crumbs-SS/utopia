package com.ss.utopia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "airport")
public class Airport {

    @Id
    @Column(name = "iata_id")
    private String airportCode;

    @Column(name = "city")
    private String cityName;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oriAirport")
    List<Route> originRoutes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "desAirport")
    List<Route> destinationRoutes = new ArrayList<>();

    public Airport(){

    }

    public Airport(String airportCode, String cityName) {
        this.airportCode = airportCode;
        this.cityName = cityName;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getCityName() {
        return cityName;
    }
}
