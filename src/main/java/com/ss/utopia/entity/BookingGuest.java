package com.ss.utopia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity(name = "booking_guest")
public class BookingGuest {

    @Id
    @Column(name = "booking_id")
    private Integer id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "booking_id")
    @JsonBackReference
    private Booking booking;

    private String contactEmail;
    private String contactPhone;


    public BookingGuest(){
    }

    public BookingGuest(Booking booking, String contactEmail, String contactPhone) {
        this.booking = booking;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
