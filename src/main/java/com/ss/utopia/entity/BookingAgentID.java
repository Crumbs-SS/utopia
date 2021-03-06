package com.ss.utopia.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookingAgentID implements Serializable {
    private Integer agentId;
    private Integer bookingId;

    public BookingAgentID() {

    }

    public BookingAgentID(Integer agentId, Integer bookingId) {
        this.agentId = agentId;
        this.bookingId = bookingId;
    }

    public Integer getAgent_id() {
        return agentId;
    }

    public void setAgent_id(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getBooking_id() {
        return bookingId;
    }

    public void setBooking_id(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingAgentID that = (BookingAgentID) o;
        return agentId.equals(that.agentId) && bookingId.equals(that.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agentId, bookingId);
    }
}
