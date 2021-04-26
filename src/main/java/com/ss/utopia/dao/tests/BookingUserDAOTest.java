package com.ss.utopia.dao.tests;


import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.dao.BookingUserDAO;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingUser;
import com.ss.utopia.entity.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingUserDAOTest {

    @Test
    void addBookingUser() throws SQLException {
        try {
            BookingUserDAO badao = new BookingUserDAO();
            BookingDAO bookingDAO = new BookingDAO();
            UserDAO udao = new UserDAO();

            User user = udao.getAllUsers().get(0);
            Booking booking = bookingDAO.getAllBookings().get(0);

            BookingUser bookingUser = new BookingUser(user, booking);
            badao.addBookingUser(bookingUser);

            assertEquals(bookingUser.getBooking().getId(), badao.getBookingUserFromUser(user).getUser().getId());

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    void deleteBookingUser() throws SQLException {
        try {
            BookingUserDAO badao = new BookingUserDAO();

            List<BookingUser> bookingUserList = badao.getAllBookingUsers();
            BookingUser bookingUser = bookingUserList.get(bookingUserList.size() - 1);

            badao.deleteBookingUser(bookingUser);

            assertNull(badao.getBookingUserFromBooking(bookingUser.getBooking()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}