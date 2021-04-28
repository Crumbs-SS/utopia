package com.ss.utopia.dto;

import java.util.List;

public class BookingDTO {
    private Integer flightId;
    private Integer userId;
    private String stripeId;
    private List<Integer> passengerIds;

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

    public String getStripeId() {
        return stripeId;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }

    public List<Integer> getPassengerIds() {
        return passengerIds;
    }

    public void setPassengerIds(List<Integer> passengerIds) {
        this.passengerIds = passengerIds;
    }
}