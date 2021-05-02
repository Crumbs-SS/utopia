package com.ss.utopia.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name = "booking_agent")
public class BookingAgent {

    @EmbeddedId
    private BookingAgentID id;

    @MapsId("bookingId")
    @ManyToOne
    private Booking booking;

    @MapsId("agentId")
    @ManyToOne
    private User agent;


    public BookingAgent(){

    }
    public BookingAgent(Booking booking, User agent) {
        this.booking = booking;
        this.agent = agent;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public User getAgent() {
        return agent;
    }

    public void setAgent(User agent) {
        this.agent = agent;
    }
}
