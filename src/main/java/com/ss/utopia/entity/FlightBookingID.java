package com.ss.utopia.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FlightBookingID implements Serializable {
    private Integer flightId;
    private Integer bookingId;

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public FlightBookingID() {

    }

    public FlightBookingID(Integer flightId, Integer bookingId) {
        this.flightId = flightId;
        this.bookingId = bookingId;

    }
}
