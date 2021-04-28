package com.ss.utopia.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Airplane {
    private Integer id;
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

    public static Airplane toObject(ResultSet rs) throws SQLException {
        Integer airplaneID = rs.getInt("id");
        Integer maxCapacity = rs.getInt("max_capacity");
        Integer airplaneTypeID = rs.getInt("type_id");

        AirplaneType airplaneType = new AirplaneType(maxCapacity);
        airplaneType.setId(airplaneTypeID);

        Airplane airplane = new Airplane(airplaneType);
        airplane.setId(airplaneID);

        return airplane;
    }

    @Override
    public String toString() {
        return airplaneType != null ?  "AirplaneID: " + id + "AirplaneType: "
                + airplaneType.toString() : "AirplaneID: " + id;
    }
}

