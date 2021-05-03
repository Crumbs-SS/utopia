package com.ss.utopia.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FlightBookingID implements Serializable {
    private Integer flightId;
    private Integer bookingId;

    public FlightBookingID() {
    }

    public FlightBookingID(Integer flightId, Integer bookingId) {
        this.flightId = flightId;
        this.bookingId = bookingId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightBookingID that = (FlightBookingID) o;
        return flightId.equals(that.flightId) && bookingId.equals(that.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, bookingId);
    }
}
