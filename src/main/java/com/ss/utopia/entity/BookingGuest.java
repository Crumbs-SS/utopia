package com.ss.utopia.entity;


import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingGuest {
    private Booking booking;
    private String contactEmail;
    private String contactPhone;

    public BookingGuest() {}

    public BookingGuest(Booking booking, String contactEmail, String contactPhone) {
        this.booking = booking;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
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

    @Override
    public String toString() {
        return "BookingGuest{" +
                "booking=" + booking +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                '}';
    }

    public static BookingGuest toObject(ResultSet rs) throws SQLException {
        Integer bookingId = rs.getInt("booking_id");
        String contactEmail = rs.getString("contact_email");
        String contactPhone = rs.getString("contact_phone");

        return new BookingGuest(new Booking(bookingId), contactEmail, contactPhone);
    }
}
