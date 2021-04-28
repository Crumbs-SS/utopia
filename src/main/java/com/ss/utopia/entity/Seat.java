package com.ss.utopia.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Seat {
   private Flight flight;
   private Integer first;
   private Integer business;
   private Integer economy;

   public Seat(Integer first, Integer business, Integer economy){
       this.first = first;
       this.business = business;
       this.economy = economy;
   }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getBusiness() {
        return business;
    }

    public void setBusiness(Integer business) {
        this.business = business;
    }

    public Integer getEconomy() {
        return economy;
    }

    public void setEconomy(Integer economy) {
        this.economy = economy;
    }

    public static Seat toObject(ResultSet rs) throws SQLException {

        Integer flightId = rs.getInt("flight_id");
        Integer first = rs.getInt("first");
        Integer business = rs.getInt("business");
        Integer economy = rs.getInt("economy");

        Seat seat = new Seat(first,business,economy);
        seat.setFlight(new Flight(flightId));

        return seat;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "flight=" + flight.toString() +
                ", first=" + first +
                ", business=" + business +
                ", economy=" + economy +
                '}';
    }
}
