package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.entity.Booking;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BookingDAOTest {
    @Test
    void addBooking() throws SQLException {
        Connection conn = null;
        try{
            BookingDAO bookingDAO = new BookingDAO();
            Booking booking = new Booking(0, "CONFIRMATION#-1");

            bookingDAO.addBooking(booking);
            assertEquals(booking.toString(), bookingDAO.getBookingByCode("CONFIRMATION#-1").toString());
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void updateBooking() throws SQLException {
        try{
            BookingDAO bookingDAO = new BookingDAO();
            Booking booking = bookingDAO.getBookingByCode("CONFIRMATION#-1");
            booking.setIsActive(1);

            bookingDAO.updateBooking(booking);
            assertEquals(booking.toString(), bookingDAO.getBookingByCode("CONFIRMATION#-1").toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void deleteBooking() throws SQLException {
        try{
            BookingDAO bookingDAO = new BookingDAO();
            Booking booking = bookingDAO.getBookingByCode("CONFIRMATION#-1");

            bookingDAO.deleteBooking(booking);
            assertNull(bookingDAO.getBookingByCode("CONFIRMATION#-1"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}