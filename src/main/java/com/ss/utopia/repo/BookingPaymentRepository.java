package com.ss.utopia.repo;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingPaymentRepository extends JpaRepository<BookingPayment, Integer>{

    @Query("SELECT bp FROM booking_payment bp WHERE bp.booking = ?1")
    BookingPayment getBookingPaymentByBooking(Booking booking);

    @Query("SELECT bp FROM booking_payment bp WHERE bp.stripeId = ?1")
    BookingPayment getBookingByStripeId(String stripeId);

    @Query("SELECT bp FROM booking_payment bp WHERE bp.booking.confirmationCode = ?1")
    BookingPayment getBookingPaymentByConfirmationCode(String confirmationCode);
}
