package com.ss.utopia.repo;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.FlightBooking;
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
class FlightBookingRepositoryTest {

    @Autowired BookingRepository bookingRepository;
    @Autowired FlightRepository flightRepository;
    @Autowired FlightBookingRepository flightBookingRepository;

    Booking booking;
    Flight flight;
    FlightBooking flightBooking;

    @BeforeEach
    void createInstances(){
        booking = new Booking(false, "TEST-BOOKING");
        flight = flightRepository.findAll().get((int)(flightRepository.count()) - 1);
        flightBooking = new FlightBooking(flight, booking);

        bookingRepository.save(booking);
        flightBookingRepository.save(flightBooking);
    }

    @AfterEach
    void deleteInstances(){
        bookingRepository.delete(booking);
    }

    @Test
    void findByFlight() {
        List<FlightBooking> flightBookings = flightBookingRepository.findByFlight(flight);

        FlightBooking flightBookingFound = null;

        for (FlightBooking flightBooking1 : flightBookings) {
            if(flightBooking1.getBooking().getId() == booking.getId())
                flightBookingFound = flightBooking1;
        }

        assertEquals(flightBookingFound.getBooking().getId(),
                flightBooking.getBooking().getId());
    }

    @Test
    void findByBooking() {
        List<FlightBooking> flightBookingFound = flightBookingRepository.findByBooking(booking);
        assertEquals(flightBookingFound.get(0).getBooking().getId(),
                flightBooking.getBooking().getId());
    }

    @Test
    void findByBookingAndFlight() {
        List<FlightBooking> flightBookingFound = flightBookingRepository.
                findByBookingAndFlight(booking, flight);

        assertEquals(flightBookingFound.get(0).getBooking().getId(),
                flightBooking.getBooking().getId());
    }
}