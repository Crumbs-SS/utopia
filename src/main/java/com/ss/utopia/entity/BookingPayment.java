package com.ss.utopia.entity;


import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.service.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingPayment {
    private Booking booking;
    private String stripeId;
    private Boolean refunded;

    public BookingPayment(String stripeId, Boolean refunded) {
        this.stripeId = stripeId;
        this.refunded = refunded;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getStripeId() {
        return stripeId;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }

    public Boolean getRefunded() {
        return refunded;
    }

    public void setRefunded(Boolean refunded) {
        this.refunded = refunded;
    }

    @Override
    public String toString() {
        return "BookingPayment{" +
                "booking=" + booking +
                ", stripeId='" + stripeId + '\'' +
                ", refunded=" + refunded +
                '}';
    }

    public static BookingPayment toObject(ResultSet rs) throws SQLException, ClassNotFoundException {
        Integer bookingId = rs.getInt("booking_id");
        String stripeId = rs.getString("stripe_id");
        Boolean refunded = rs.getBoolean("refunded");

        Booking booking = new Booking(bookingId);
        BookingPayment bookingPayment = new BookingPayment(stripeId, refunded);
        bookingPayment.setBooking(booking);

        return bookingPayment;
    }
}
