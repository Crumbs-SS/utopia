package com.ss.utopia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean isActive;
    private String confirmationCode;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "booking")
    private List<FlightBooking> flightBookings = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    @Valid
    private List<Passenger> passengers = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<BookingUser> bookingUsers = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<BookingAgent> bookingAgents = new ArrayList<>();

    @JsonManagedReference
    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private BookingPayment bookingPayment;

    @JsonManagedReference
    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private BookingGuest bookingGuest;

    public Booking(){}

    public Booking(Boolean isActive, String confirmationCode) {
        this.isActive = isActive;
        this.confirmationCode = confirmationCode;
    }

    public Booking(Integer bookingId) {
        this.id = bookingId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public List<FlightBooking> getFlightBookings() {
        return flightBookings;
    }

    public void setFlightBookings(List<FlightBooking> flightBookings) {
        this.flightBookings = flightBookings;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<BookingUser> getBookingUsers() {
        return bookingUsers;
    }

    public void setBookingUsers(List<BookingUser> bookingUsers) {
        this.bookingUsers = bookingUsers;
    }

    public List<BookingAgent> getBookingAgents() {
        return bookingAgents;
    }

    public void setBookingAgents(List<BookingAgent> bookingAgents) {
        this.bookingAgents = bookingAgents;
    }

    public BookingPayment getBookingPayment() {
        return bookingPayment;
    }

    public void setBookingPayment(BookingPayment bookingPayment) {
        this.bookingPayment = bookingPayment;
    }

    public BookingGuest getBookingGuest() {
        return bookingGuest;
    }

    public void setBookingGuest(BookingGuest bookingGuest) {
        this.bookingGuest = bookingGuest;
    }
}
