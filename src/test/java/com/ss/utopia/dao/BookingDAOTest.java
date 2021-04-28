package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookingDAOTest {

    @Autowired
    BookingDAO bookingDAO;

    @Test
    @Order(1)
    void addBooking() throws SQLException, ClassNotFoundException{
        Booking booking = new Booking(false, "CONFIRMATION#-1");

        int numberOfBookings = bookingDAO.getAllBookings().size();
        bookingDAO.addBooking(booking);
        assertEquals(numberOfBookings + 1, bookingDAO.getAllBookings().size());
    }

    @Test
    @Order(2)
    void updateBooking() throws SQLException, ClassNotFoundException{
        Booking booking = bookingDAO.getBookingByCode("CONFIRMATION#-1");
        booking.setIsActive(true);

        bookingDAO.updateBooking(booking);
        assertEquals(booking.toString(), bookingDAO.getBookingByCode("CONFIRMATION#-1").toString());
    }

    @Test
    @Order(3)
    void deleteBooking() throws SQLException, ClassNotFoundException{
        Booking booking = bookingDAO.getBookingByCode("CONFIRMATION#-1");

        bookingDAO.deleteBooking(booking);
        assertNull(bookingDAO.getBookingByCode("CONFIRMATION#-1"));
    }
}