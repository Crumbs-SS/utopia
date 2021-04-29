package com.ss.utopia.dto;

import com.ss.utopia.entity.Passenger;

import java.util.List;

public class BookingDTO {
    private Integer flightId;
    private Integer userId;
    private String stripeId;
    private List<Passenger> passengers;

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

    public List<Passenger> getPassengers() {
        return passengers;
    }

}