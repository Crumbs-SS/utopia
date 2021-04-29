package com.ss.utopia.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "flight")
public class Flight {

    @Id
    private Integer id;

    @ManyToOne
    private Route route;

    @ManyToOne
    private Airplane airplane;

    @Column(name = "departure_time")
    private String departTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flight")
    private List<FlightBooking> flightBookings = new ArrayList<>();

    @OneToOne(mappedBy = "flight", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Seats seats;

    private Integer reservedSeats;
    private Float seatPrice;

    public Flight(){

    }

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

    public List<FlightBooking> getFlightBookings() {
        return flightBookings;
    }

    public void setFlightBookings(List<FlightBooking> flightBookings) {
        this.flightBookings = flightBookings;
    }

    public void addFlightBooking(FlightBooking flightBooking){
        flightBookings.add(flightBooking);
    }

}
