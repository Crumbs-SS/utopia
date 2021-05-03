package com.ss.utopia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ss.utopia.dto.BookingDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity(name = "passenger")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JsonIgnore
    private Booking booking;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String givenName;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String familyName;


    @Column(name = "dob")
    @NotNull
    private Date date;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 45)
    private String gender;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 45)
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

    public void setId(Integer id) {
        this.id = id;
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
