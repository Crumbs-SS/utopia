package com.ss.utopia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class BookingUser {

    @EmbeddedId
    private BookingUserID id;

    @MapsId("userId")
    @ManyToOne
    private User user;

    @MapsId("bookingId")
    @ManyToOne
    @JsonBackReference
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

    public BookingUserID getId() {
        return id;
    }

    public void setId(BookingUserID id) {
        this.id = id;
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
