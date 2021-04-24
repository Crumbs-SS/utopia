package com.ss.utopia.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Airport {
    private String airportCode;
    private String cityName;

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

    public static Airport toObject(ResultSet rs) throws SQLException {
        String iataCode = rs.getString("iata_id");
        String city = rs.getString("city");

        return new Airport(iataCode, city);
    }

    @Override
    public String toString() {
        return airportCode + ": " + cityName;
    }
}
