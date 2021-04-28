package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Passenger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PassengerDAOTest {

    @Autowired
    PassengerDAO pdao;

    @Autowired
    BookingDAO bookingDAO;

    @Test
    @Order(1)
    void addPassenger() throws SQLException, ClassNotFoundException {
        Booking booking = bookingDAO.getAllBookings().get(0);
        Date date = Date.valueOf(LocalDate.now());
        Passenger passenger = new Passenger(booking, "ELIJAH", "BROOKS", date, "MALE", "LANE");
        int allPassengersSize = pdao.getAllPassengers().size();
        pdao.addPassenger(passenger);

        assertEquals(allPassengersSize + 1, pdao.getAllPassengers().size());
    }

    @Test
    @Order(2)
    void updatePassenger() throws SQLException, ClassNotFoundException {
        Booking booking = bookingDAO.getAllBookings().get(0);

        List<Passenger> passengers = pdao.getPassengersByBooking(booking);
        Passenger passenger = passengers.get(0);
        passenger.setAddress("13134 LANE");

        pdao.updatePassenger(passenger);
        assertEquals(passenger.getAddress(), pdao.getPassenger(passenger.getId()).getAddress());
    }

    @Test
    @Order(3)
    void deletePassenger() throws SQLException, ClassNotFoundException {
        Booking booking = bookingDAO.getAllBookings().get(0);;

        List<Passenger> passengers = pdao.getPassengersByBooking(booking);
        Passenger passenger = passengers.get(0);

        pdao.deletePassenger(passenger);
        assertNull(pdao.getPassenger(passenger.getId()));
    }
}