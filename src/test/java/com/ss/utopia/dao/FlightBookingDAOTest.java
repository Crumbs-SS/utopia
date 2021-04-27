package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.FlightBooking;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FlightBookingDAOTest {

    @Autowired
    FlightBookingDAO fbdao;

    @Autowired
    FlightDAO fdao;

    @Autowired
    BookingDAO bookingDAO;

    @Test
    @Order(1)
    void addFlightBooking() throws SQLException, ClassNotFoundException {
        Flight flight = fdao.getAllFlights().get(fdao.getAllFlights().size() - 1);
        flight = fdao.getFlightFromId(flight.getId());

        Booking booking = bookingDAO.getAllBookings().get(bookingDAO.getAllBookings().size() - 1);
        booking = bookingDAO.getBookingById(booking.getId());

        FlightBooking flightBooking = new FlightBooking(flight, booking);
        fbdao.addFlightBooking(flightBooking);

        assertEquals(flightBooking.getFlight().getId(), fbdao.getFlightBookingByBooking(booking).getFlight().getId());
    }

    @Test
    @Order(2)
    void deleteFlightBooking()throws SQLException, ClassNotFoundException {
        List<FlightBooking> flightBookingList = fbdao.getAllFlightBookings();
        FlightBooking flightBooking = flightBookingList.get(flightBookingList.size() - 1);

        fbdao.deleteFlightBooking(flightBooking);
        assertNull(fbdao.getFlightBookingByBooking(flightBooking.getBooking()));
    }
}