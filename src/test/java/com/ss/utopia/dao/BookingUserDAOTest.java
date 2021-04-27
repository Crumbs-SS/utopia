package com.ss.utopia.dao;


import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingUser;
import com.ss.utopia.entity.User;
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
class BookingUserDAOTest {

    @Autowired
    BookingUserDAO badao;

    @Autowired
    BookingDAO bookingDAO;

    @Autowired
    UserDAO udao;

    @Test
    @Order(1)
     void addBookingUser() throws SQLException, ClassNotFoundException {
        User user = udao.getAllUsers().get(0);
        Booking booking = bookingDAO.getAllBookings().get(0);
        int allBookingUsers = badao.getAllBookingUsers().size();

        BookingUser bookingUser = new BookingUser(user, booking);
        badao.addBookingUser(bookingUser);

        assertEquals(allBookingUsers + 1, badao.getAllBookingUsers().size());
    }

    @Test
    @Order(2)
    void deleteBookingUser() throws SQLException, ClassNotFoundException {
        List<BookingUser> bookingUserList = badao.getAllBookingUsers();
        BookingUser bookingUser = bookingUserList.get(bookingUserList.size() - 1);

        badao.deleteBookingUser(bookingUser);
        assertNull(badao.getBookingUserFromBooking(bookingUser.getBooking()));
    }
}