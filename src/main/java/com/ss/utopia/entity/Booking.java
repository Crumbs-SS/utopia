package com.ss.utopia.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Booking {
    private Integer id;
    private Integer isActive;
    private String confirmationCode;

    public Booking(Integer isActive, String confirmationCode) {
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

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", confirmationCode='" + confirmationCode + '\'' +
                '}';
    }

    public static Booking toObject(ResultSet rs) throws SQLException {
        Integer bookingId = rs.getInt("id");
        Integer isActive = rs.getInt("is_active");
        String confirmationCode = rs.getString("confirmation_code");

        Booking booking = new Booking(isActive, confirmationCode);
        booking.setId(bookingId);

        return booking;
    }
}
