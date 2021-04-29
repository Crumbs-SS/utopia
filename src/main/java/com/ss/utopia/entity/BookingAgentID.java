package com.ss.utopia.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BookingAgentID implements Serializable {
    private Integer agent_id;
    private Integer booking_id;
}
