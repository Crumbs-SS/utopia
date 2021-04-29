package com.ss.utopia.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "airport")
public class Airport {

    @Id
    private String airportCode;
    private String cityName;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oriAirport")
    List<Route> originRoutes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destAirport")
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
