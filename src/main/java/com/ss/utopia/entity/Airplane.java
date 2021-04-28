package com.ss.utopia.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "airplane")
public class Airplane {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name="type_id")
    private AirplaneType airplaneType;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public AirplaneType getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(AirplaneType airplaneType) {
        this.airplaneType = airplaneType;
    }
}

