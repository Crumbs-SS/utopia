package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.dao.BookingGuestDAO;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingGuest;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BookingGuestDAOTest {
    @Test
    void addBookingGuest() throws SQLException {
        Connection conn = null;
        try{
            BookingGuestDAO bgdao = new BookingGuestDAO();
            BookingDAO bookingDAO = new BookingDAO();

            Booking booking = bookingDAO.getAllBookings().get(0);

            BookingGuest bookingGuest = new BookingGuest(booking, "CONTACT@GMAIL", "555111555");
            bgdao.addBookingGuest(bookingGuest);

            assertEquals(bookingGuest.getBooking().getId(), bgdao.getBookingGuestByEmail("CONTACT@GMAIL").getBooking().getId());
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteBookingGuest() throws SQLException {
        try{
            BookingGuestDAO bgdao = new BookingGuestDAO();

            BookingGuest bookingGuest = bgdao.getBookingGuestByEmail("CONTACT@GMAIL");
            bgdao.deleteBookingGuest(bookingGuest);

            assertNull(bgdao.getBookingGuestByEmail("CONTACT@GMAIL"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}