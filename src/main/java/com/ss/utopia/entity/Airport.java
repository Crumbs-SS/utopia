package com.ss.utopia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "airport")
public class Airport {

    @Id
    @Column(name = "iata_id")
    @NotNull
    @NotBlank
    @Size(min = 3, max = 3)
    private String airportCode;

    @Column(name = "city")
    @NotBlank
    @Size(min = 1, max = 45)
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

    public List<Route> getOriginRoutes() {
        return originRoutes;
    }

    public void setOriginRoutes(List<Route> originRoutes) {
        this.originRoutes = originRoutes;
    }

    public List<Route> getDestinationRoutes() {
        return destinationRoutes;
    }

    public void setDestinationRoutes(List<Route> destinationRoutes) {
        this.destinationRoutes = destinationRoutes;
    }
}
