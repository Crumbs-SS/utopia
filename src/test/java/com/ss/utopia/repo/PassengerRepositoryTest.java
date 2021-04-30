package com.ss.utopia.repo;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Passenger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PassengerRepositoryTest {

    @Autowired PassengerRepository passengerRepository;
    @Autowired BookingRepository bookingRepository;

    Passenger passenger;
    Booking booking;

    @BeforeEach
    void createInstances(){
        booking = new Booking(false, "TEST-BOOKING");
        passenger = new Passenger(
                booking,
                "TEST",
                "PERSON",
                Date.valueOf("2021-08-12"),
                "MALE",
                "ADDRESS"
        );

        bookingRepository.save(booking);
        passengerRepository.save(passenger);
    }

    @AfterEach
    void deleteInstances(){
        bookingRepository.delete(booking);
    }

    @Test
    void getPassengersByBooking() {
        List<Passenger> passengers = passengerRepository.getPassengersByBooking(booking);
        Passenger passengerFound = null;

        for (Passenger passenger1 : passengers) {
            if(passenger1.getId() == passenger.getId())
                passengerFound = passenger;
        }

        assertEquals(passenger.getId(), passengerFound.getId());
    }
}