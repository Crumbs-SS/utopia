package com.ss.utopia.entity;


import javax.persistence.*;

@Entity(name = "flight_bookings")
public class FlightBooking {

    @EmbeddedId
    private FlightBookingID id;

    @MapsId("flight_id")
    @ManyToOne
    Flight flight;

    @MapsId("booking_id")
    @ManyToOne
    Booking booking;

    public FlightBooking() {

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
