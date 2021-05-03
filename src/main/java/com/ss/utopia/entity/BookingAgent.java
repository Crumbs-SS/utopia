package com.ss.utopia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name = "booking_agent")
public class BookingAgent {

    @EmbeddedId
    private BookingAgentID id;

    @JsonBackReference
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
        this.id = new BookingAgentID(agent.getId(), booking.getId());
    }

    public BookingAgentID getId() {
        return id;
    }

    public void setId(BookingAgentID id) {
        this.id = id;
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
