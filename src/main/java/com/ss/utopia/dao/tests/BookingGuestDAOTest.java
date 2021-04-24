package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.dao.BookingGuestDAO;
import com.ss.utopia.dao.BookingUserDAO;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingGuest;
import com.ss.utopia.entity.User;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BookingGuestDAOTest {
    ConnectionUtil connUtil = new ConnectionUtil();

    @Test
    void addBookingGuest() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            BookingGuestDAO bgdao = new BookingGuestDAO(conn);
            BookingDAO bookingDAO = new BookingDAO(conn);

            Booking booking = bookingDAO.getAllBookings().get(0);

            BookingGuest bookingGuest = new BookingGuest(booking, "CONTACT@GMAIL", "555111555");
            bgdao.addBookingGuest(bookingGuest);

            assertEquals(bookingGuest.getBooking().getId(), bgdao.getBookingGuestByEmail("CONTACT@GMAIL").getBooking().getId());
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            conn.close();
        }
    }

    @Test
    void deleteBookingGuest() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            BookingGuestDAO bgdao = new BookingGuestDAO(conn);


            BookingGuest bookingGuest = bgdao.getBookingGuestByEmail("CONTACT@GMAIL");
            bgdao.deleteBookingGuest(bookingGuest);

            assertNull(bgdao.getBookingGuestByEmail("CONTACT@GMAIL"));
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            conn.close();
        }
    }
}