package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingAgent;
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
class BookingAgentDAOTest {

    @Autowired
    BookingAgentDAO badao;

    @Autowired
    BookingDAO bookingDAO;

    @Autowired
    UserDAO udao;


    @Test
    @Order(1)
    void addBookingAgent() throws SQLException, ClassNotFoundException {
        User user = udao.getAllUsers().get(0);
        Booking booking = bookingDAO.getAllBookings().get(0);

        BookingAgent bookingAgent = new BookingAgent(booking, user);
        int numberOfBookingAgents = badao.getAllBookingAgents().size();
        badao.addBookingAgent(bookingAgent);

        assertEquals(numberOfBookingAgents + 1, badao.getAllBookingAgents().size());
    }

    @Test
    @Order(2)
    void deleteBookingAgent() throws SQLException, ClassNotFoundException{
        List<BookingAgent> bookingAgents = badao.getAllBookingAgents();
        BookingAgent bookingAgent = bookingAgents.get(bookingAgents.size() - 1);

        badao.deleteBookingAgent(bookingAgent);
        assertNull(badao.getBookingAgentFromBooking(bookingAgent.getBooking()));
    }

}