package com.ss.utopia.entity;


import javax.persistence.*;

@Entity(name = "flight_bookings")
public class FlightBooking {

    @EmbeddedId
    private FlightBookingID id;

    @MapsId("flightId")
    @ManyToOne
    Flight flight;

    @MapsId("bookingId")
    @ManyToOne
    Booking booking;

    public FlightBooking() {

    }

    public FlightBooking(Flight flight, Booking booking){
        this.flight = flight;
        this.booking = booking;
        this.id = new FlightBookingID(flight.getId(), booking.getId());
    }

    public FlightBooking(FlightBookingID flightBookingID) {
        this.id = flightBookingID;
    }

    public void setId(FlightBookingID id) {
        this.id = id;
    }

    public FlightBookingID getId() {
        return id;
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
