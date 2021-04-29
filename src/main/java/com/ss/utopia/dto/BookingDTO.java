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

    public Integer getUserId() {
        return userId;
    }


    public String getStripeId() {
        return stripeId;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
}