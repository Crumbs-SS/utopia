package com.ss.utopia.repo;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingGuest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookingGuestRepositoryTest {

    @Autowired BookingRepository bookingRepository;
    @Autowired BookingGuestRepository bookingGuestRepository;

    Booking booking;
    BookingGuest bookingGuest;

    @BeforeEach
    void createInstances(){
        booking = new Booking(false, "TEST-BOOKING");
        bookingGuest = new BookingGuest(booking, "TEST-GMAIL", "123");

        bookingRepository.save(booking);
        bookingGuestRepository.save(bookingGuest);
    }

    @AfterEach
    void deleteInstances(){
        bookingRepository.delete(booking);
    }

    @Test
    void getBookingGuestByEmail() {
        BookingGuest bookingGuestFound = bookingGuestRepository.getBookingGuestByEmail("TEST-GMAIL");

        assertEquals(bookingGuestFound.getBooking().getId(),
                bookingGuest.getBooking().getId());
    }

    @Test
    void getBookingGuestByBooking() {
        BookingGuest bookingGuestFound = bookingGuestRepository.getBookingGuestByBooking(booking);

        assertEquals(bookingGuestFound.getBooking().getId(),
                bookingGuest.getBooking().getId());
    }
}