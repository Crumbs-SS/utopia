package com.ss.utopia.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Route {
    private Integer id;
    private Airport oriAirport;
    private Airport desAirport;


    public void setOriAirport(Airport oriAirport) {
        this.oriAirport = oriAirport;
    }

    public void setDesAirport(Airport desAirport) {
        this.desAirport = desAirport;
    }

    public Route(Integer routeId){
        this.id = routeId;
    }

    public Route(Airport oriAirport, Airport desAirport) {
        this.oriAirport = oriAirport;
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

    public static Route toObject(ResultSet rs) throws SQLException {
        String originID = rs.getString("origin_id");
        String originCity = rs.getString("origin_city");
        String destinationID = rs.getString("destination_id");
        String destinationCity = rs.getString("destination_city");
        Integer routeID = rs.getInt("id");

        Airport origin = new Airport(originID, originCity);
        Airport destination = new Airport(destinationID, destinationCity);
        Route route = new Route(origin, destination);

        route.setId(routeID);
        return route;
    }

    @Override
    public String toString() {
        return oriAirport != null && desAirport != null ? "ID: " + id + " - " + oriAirport.getAirportCode() + ": " +
                oriAirport.getCityName() +
                " => " + oriAirport.getAirportCode() +": " +
                desAirport.getCityName() : "ID: " + id;
    }
}
