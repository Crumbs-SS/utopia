package com.ss.utopia.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "airplane_type")
public class AirplaneType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer maxCapacity;

    @JsonIgnore
    @OneToMany(mappedBy = "airplaneType", cascade = CascadeType.ALL)
    private List<Airplane> airplanes = new ArrayList<>();

    public AirplaneType() {}
    public AirplaneType(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public List<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(List<Airplane> airplanes) {
        this.airplanes = airplanes;
    }
}
