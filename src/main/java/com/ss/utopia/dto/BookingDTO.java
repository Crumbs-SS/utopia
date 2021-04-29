package com.ss.utopia.dto;

import com.ss.utopia.entity.BookingGuest;

import java.util.List;

public class BookingDTO {
    private Integer id;
    private Integer flightId;
    private Integer userId;
    private BookingGuest bookingGuest;
    private String stripeId;
    private String confirmationCode;
    private List<Integer> passengerIds;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BookingGuest getBookingGuest() {
        return bookingGuest;
    }

    public void setBookingGuest(BookingGuest bookingGuest) {
        this.bookingGuest = bookingGuest;
    }

    public String getStripeId() {
        return stripeId;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public List<Integer> getPassengerIds() {
        return passengerIds;
    }

    public void setPassengerIds(List<Integer> passengerIds) {
        this.passengerIds = passengerIds;
    }
}