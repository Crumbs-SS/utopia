package com.ss.utopia.entity;


import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingUser {
    private User user;
    private Booking booking;

    public BookingUser(User user, Booking booking) {
        this.user = user;
        this.booking = booking;
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

    @Override
    public String toString() {
        return "BookingUser{" +
                "user=" + user +
                ", booking=" + booking +
                '}';
    }

    public static BookingUser toObject(ResultSet rs) throws SQLException {
        Integer bookingId = rs.getInt("booking_id");
        Integer userId = rs.getInt("user_id");

        Booking booking = new Booking(bookingId);
        User user = new User(userId);

        BookingUser bookingUser = new BookingUser(user, booking);
        return bookingUser;
    }
}
