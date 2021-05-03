package com.ss.utopia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @NotNull
    @Valid
    private Route route;

    @ManyToOne
    @NotNull
    @Valid
    private Airplane airplane;

    // STILL NOT ACCOUNTING FOR BAD STRINGS
    @Column(name = "departure_time")
    @NotNull
    private String departTime;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flight")
    private List<FlightBooking> flightBookings = new ArrayList<>();

//    @OneToOne(mappedBy = "flight", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    @JsonManagedReference
//    @NotNull
//    @Valid
//    private Seats seats;

    @NotNull
    @Min(value = 0)
    private Integer reservedSeats;

    @NotNull
    @Min(value = 0)
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

    public List<FlightBooking> getFlightBookings() {
        return flightBookings;
    }

    public void setFlightBookings(List<FlightBooking> flightBookings) {
        this.flightBookings = flightBookings;
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
}
