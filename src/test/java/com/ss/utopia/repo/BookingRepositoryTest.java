package com.ss.utopia.repo;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingPayment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookingRepositoryTest {

    @Autowired BookingRepository bookingRepository;

    Booking booking;

    @BeforeEach
    void createInstances(){
        booking = new Booking(false, "TEST-BOOKING");
        bookingRepository.save(booking);
    }

    @AfterEach
    void deleteInstances(){
        bookingRepository.delete(booking);
    }

    @Test
    void getBookingsByConfirmationCode() {
        List<Booking> bookingFound = bookingRepository.getBookingsByConfirmationCode("TEST-BOOKING");
        assertEquals(bookingFound.get(0).getId(), booking.getId());
    }
}