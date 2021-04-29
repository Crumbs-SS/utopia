package com.ss.utopia.entity;


import javax.persistence.*;

@Entity(name = "flight_bookings")
public class FlightBooking {

    @EmbeddedId
    private FlightBookingID id;

    @MapsId("flight_id")
    @ManyToOne(optional = false)
    Flight flight;

    @MapsId("booking_id")
    @ManyToOne(optional = false)
    Booking booking;

    public FlightBooking() {

    }

    public FlightBooking(Flight flight, Booking booking){
        this.flight = flight;
        this.booking = booking;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }


}
