package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BookingDAOTest {
    ConnectionUtil connUtil = new ConnectionUtil();

    @Test
    void addBooking() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            BookingDAO bookingDAO = new BookingDAO(conn);
            Booking booking = new Booking(0, "CONFIRMATION#-1");

            bookingDAO.addBooking(booking);
            assertEquals(booking.toString(), bookingDAO.getBookingByCode("CONFIRMATION#-1").toString());
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            conn.close();
        }
    }

    @Test
    void updateBooking() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            BookingDAO bookingDAO = new BookingDAO(conn);
            Booking booking = bookingDAO.getBookingByCode("CONFIRMATION#-1");
            booking.setIsActive(1);

            bookingDAO.updateBooking(booking);
            assertEquals(booking.toString(), bookingDAO.getBookingByCode("CONFIRMATION#-1").toString());
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            conn.close();
        }
    }

    @Test
    void deleteBooking() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            BookingDAO bookingDAO = new BookingDAO(conn);
            Booking booking = bookingDAO.getBookingByCode("CONFIRMATION#-1");

            bookingDAO.deleteBooking(booking);
            assertNull(bookingDAO.getBookingByCode("CONFIRMATION#-1"));
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            conn.close();
        }
    }
}