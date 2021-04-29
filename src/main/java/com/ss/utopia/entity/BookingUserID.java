package com.ss.utopia.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BookingUserID implements Serializable {
    private Integer user_id;
    private Integer booking_id;
}
