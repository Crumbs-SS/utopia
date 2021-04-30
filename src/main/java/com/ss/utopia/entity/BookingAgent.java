package com.ss.utopia.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name = "booking_agent")
public class BookingAgent {

    @EmbeddedId
    private BookingAgentID id;

    @MapsId("booking_id")
    @ManyToOne
    private Booking booking;

    @MapsId("agent_id")
    @ManyToOne
    private User agent;


    public BookingAgent(){

    }
    public BookingAgent(Booking booking, User agent) {
        this.booking = booking;
        this.agent = agent;
        this.id = new BookingAgentID(agent.getId(), booking.getId());
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
