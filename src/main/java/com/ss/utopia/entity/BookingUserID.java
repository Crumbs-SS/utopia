package com.ss.utopia.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookingUserID implements Serializable {
    private Integer userId;
    private Integer bookingId;

    public BookingUserID() {
    }

    public BookingUserID(Integer userId, Integer bookingId) {
        this.userId = userId;
        this.bookingId = bookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingUserID that = (BookingUserID) o;
        return userId.equals(that.userId) && bookingId.equals(that.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, bookingId);
    }
}
