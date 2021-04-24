package com.ss.utopia.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingAgent {
    private Booking booking;
    private User agent;

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

    @Override
    public String toString() {
        return "BookingAgent{" +
                "booking=" + booking +
                ", agent=" + agent +
                '}';
    }

    public static BookingAgent toObject(ResultSet rs) throws SQLException {
        Integer bookingId = rs.getInt("booking_id");
        Integer agentId = rs.getInt("agent_id");

        Booking booking = new Booking(bookingId);
        User agent = new User(agentId);

        BookingAgent bookingAgent = new BookingAgent(booking, agent);
        return bookingAgent;
    }
}
