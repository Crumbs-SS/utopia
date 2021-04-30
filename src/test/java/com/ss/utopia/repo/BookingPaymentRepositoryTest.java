package com.ss.utopia.repo;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingPayment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookingPaymentRepositoryTest {
    @Autowired BookingPaymentRepository bookingPaymentRepository;
    @Autowired BookingRepository bookingRepository;

    BookingPayment bookingPayment;
    Booking booking;

    @BeforeEach
    void createInstances(){
        booking = new Booking(false, "TEST-BOOKING");
        bookingPayment = new BookingPayment(booking, "TEST-STRIPE", false);

        bookingRepository.save(booking);
        bookingPaymentRepository.save(bookingPayment);
    }

    @AfterEach
    void deleteInstances(){
        bookingRepository.delete(booking);
    }

    @Test
    void getBookingPaymentByBooking() {
        BookingPayment bookingPaymentFound = bookingPaymentRepository.getBookingPaymentByBooking(booking);

        assertEquals(bookingPaymentFound.getBooking().getId(),
                bookingPayment.getBooking().getId());
    }

    @Test
    void getBookingByStripeId() {
        BookingPayment bookingPaymentFound = bookingPaymentRepository.getBookingByStripeId("TEST-STRIPE");

        assertEquals(bookingPaymentFound.getBooking().getId(),
                bookingPayment.getBooking().getId());
    }
}