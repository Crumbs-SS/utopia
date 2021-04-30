package com.ss.utopia.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BookingAgentID implements Serializable {
    private Integer agent_id;
    private Integer booking_id;

    public BookingAgentID() {

    }

    public BookingAgentID(Integer agent_id, Integer booking_id) {
        this.agent_id = agent_id;
        this.booking_id = booking_id;
    }

    public Integer getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(Integer agent_id) {
        this.agent_id = agent_id;
    }

    public Integer getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Integer booking_id) {
        this.booking_id = booking_id;
    }


}
