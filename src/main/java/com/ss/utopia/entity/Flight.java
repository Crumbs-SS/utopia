package com.ss.utopia.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Flight {
    private Integer id;
    private Route route;
    private Airplane airplane;

   // @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS",timezone="PST")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private String departTime;
    private Integer reservedSeats;
    private Float seatPrice;


    public Flight(String departTime, Integer reservedSeats, Float seatPrice) {
        this.departTime = departTime;
        this.reservedSeats = reservedSeats;
        this.seatPrice = seatPrice;
    }

    public Flight(Integer flightId) {
        this.id = flightId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public Integer getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(Integer reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public Float getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(Float seatPrice) {
        this.seatPrice = seatPrice;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", route=" + route +
                ", airplane=" + airplane +
                ", departTime=" + departTime +
                ", reservedSeats=" + reservedSeats +
                ", seatPrice=" + seatPrice +
                '}';
    }

    public static Flight toObject(ResultSet rs) throws SQLException {
        Integer flightId = rs.getInt("id");
        Integer routeId = rs.getInt("route_id");
        Integer airplaneID = rs.getInt("airplane_id");
        String departureTime = rs.getString("departure_time");

        Integer reservedSeats = rs.getInt("reserved_seats");
        Float seatPrice = rs.getFloat("seat_price");

        Flight flight = new Flight(departureTime, reservedSeats, seatPrice);
        flight.setId(flightId);
        flight.setRoute(new Route(routeId));
        flight.setAirplane(new Airplane(airplaneID));

        return flight;
    }
}
