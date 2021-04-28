package com.ss.utopia.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AirplaneType {
    private Integer id;
    private Integer maxCapacity;

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

    public static AirplaneType toObject(ResultSet rs) throws SQLException {
        Integer airplaneTypeID = rs.getInt("id");
        Integer airplaneTypeMaxCapacity = rs.getInt("max_capacity");

        AirplaneType airplaneType = new AirplaneType(airplaneTypeMaxCapacity);
        airplaneType.setId(airplaneTypeID);

        return airplaneType;
    }

    @Override
    public String toString() {
        return "Airplane ID: " + id + " | Max Capacity: " + maxCapacity;
    }
}
