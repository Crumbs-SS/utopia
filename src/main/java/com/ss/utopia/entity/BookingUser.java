package com.ss.utopia.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class BookingUser {

    @EmbeddedId
    private BookingUserID id;

    @MapsId("user_id")
    @ManyToOne
    private User user;

    @MapsId("booking_id")
    @ManyToOne
    private Booking booking;

    public BookingUser(User user, Booking booking) {
        this.user = user;
        this.booking = booking;
    }

    public BookingUser() {
    }

    public BookingUser(Booking booking, User user) {
        this.id = new BookingUserID(user.getId(), booking.getId());
        this.booking = booking;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
