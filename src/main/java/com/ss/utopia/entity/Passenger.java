package com.ss.utopia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity(name = "passenger")
public class Passenger {

    @Id
    private Integer id;

    @ManyToOne
    @JsonIgnore
    private Booking booking;

    private String givenName;
    private String familyName;

    @Column(name = "dob")
    private Date date;
    private String gender;
    private String address;

    public Passenger(Booking booking, String givenName, String familyName, Date date, String gender, String address) {
        this.booking = booking;
        this.givenName = givenName;
        this.familyName = familyName;
        this.date = date;
        this.gender = gender;
        this.address = address;
    }

    public Passenger() {

    }

    public Integer getId() {
        return id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
