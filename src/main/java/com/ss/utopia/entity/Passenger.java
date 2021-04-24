package com.ss.utopia.entity;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Passenger {
    private Integer id;
    private Booking booking;
    private String given_name;
    private String family_name;
    private Date date;
    private String gender;
    private String address;
    private String email;
    private String phone;

    public Passenger(Booking booking, String given_name, String family_name, Date date, String gender, String address) {
        this.booking = booking;
        this.given_name = given_name;
        this.family_name = family_name;
        this.date = date;
        this.gender = gender;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
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

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", booking=" + booking +
                ", given_name='" + given_name + '\'' +
                ", family_name='" + family_name + '\'' +
                ", date=" + date +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static Passenger toObject(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        Integer bookingId = rs.getInt("booking_id");
        String givenName = rs.getString("given_name");
        String familyName = rs.getString("family_name");
        Date date = rs.getDate("dob");
        String gender = rs.getString("gender");
        String address = rs.getString("address");

        Booking booking = new Booking(bookingId);
        Passenger passenger = new Passenger(booking, givenName, familyName, date, gender, address);
        passenger.setId(id);
        return passenger;
    }
}
