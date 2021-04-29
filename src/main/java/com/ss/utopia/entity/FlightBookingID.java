package com.ss.utopia.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FlightBookingID implements Serializable {
    private Integer flight_id;
    private Integer booking_id;
}
