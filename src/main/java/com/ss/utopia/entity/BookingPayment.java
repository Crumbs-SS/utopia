package com.ss.utopia.entity;

import javax.persistence.*;

@Entity(name = "booking_payment")
public class BookingPayment {
    @Id
    @Column(name = "booking_id")
    private Integer id;


    @OneToOne
    @MapsId
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private String stripeId;
    private Boolean refunded;

    public BookingPayment(){

    }

    public BookingPayment(String stripeId, Boolean refunded) {
        this.stripeId = stripeId;
        this.refunded = refunded;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getStripeId() {
        return stripeId;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }

    public Boolean getRefunded() {
        return refunded;
    }

    public void setRefunded(Boolean refunded) {
        this.refunded = refunded;
    }

}
