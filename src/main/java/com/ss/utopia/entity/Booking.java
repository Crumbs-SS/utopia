package com.ss.utopia.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "booking")
public class Booking {

    @Id
    private Integer id;
    private Boolean isActive;
    private String confirmationCode;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<FlightBooking> flightBookings = new ArrayList<>();

    public Booking(Boolean isActive, String confirmationCode) {
        this.isActive = isActive;
        this.confirmationCode = confirmationCode;
    }

    public Booking(){}

    public Booking(Integer bookingId) {
        this.id = bookingId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
}
