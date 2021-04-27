package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingGuest;
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
class BookingGuestDAOTest {

    @Autowired
    BookingGuestDAO bgdao;

    @Autowired
    BookingDAO bookingDAO;

    @Test
    @Order(1)
     void addBookingGuest() throws SQLException, ClassNotFoundException{
        Booking booking = bookingDAO.getAllBookings().get(0);

        BookingGuest bookingGuest = new BookingGuest(booking, "CONTACT@GMAIL", "555111555");
        bgdao.addBookingGuest(bookingGuest);

        assertEquals(bookingGuest.getBooking().getId(), bgdao.getBookingGuestByEmail("CONTACT@GMAIL").getBooking().getId());
    }

    @Test
    @Order(2)
    void deleteBookingGuest() throws SQLException, ClassNotFoundException{
        BookingGuest bookingGuest = bgdao.getBookingGuestByEmail("CONTACT@GMAIL");
        bgdao.deleteBookingGuest(bookingGuest);

        assertNull(bgdao.getBookingGuestByEmail("CONTACT@GMAIL"));
    }
}