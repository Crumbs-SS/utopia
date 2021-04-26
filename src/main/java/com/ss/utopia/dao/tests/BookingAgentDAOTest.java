package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.*;
import com.ss.utopia.entity.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingAgentDAOTest {

    @Test
    void addBookingAgent() throws SQLException, ClassNotFoundException {
        try {
            BookingAgentDAO badao = new BookingAgentDAO();
            BookingDAO bookingDAO = new BookingDAO();
            UserDAO udao = new UserDAO();

            User user = udao.getAllUsers().get(0);
            Booking booking = bookingDAO.getAllBookings().get(0);

            BookingAgent bookingAgent = new BookingAgent(booking, user);
            badao.addBookingAgent(bookingAgent);

            assertEquals(bookingAgent.getBooking().getId(), badao.getBookingAgentFromAgent(user).getBooking().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteBookingAgent() throws SQLException {
        try {
            BookingAgentDAO badao = new BookingAgentDAO();

            List<BookingAgent> bookingAgents = badao.getAllBookingAgents();
            BookingAgent bookingAgent = bookingAgents.get(bookingAgents.size() - 1);

            badao.deleteBookingAgent(bookingAgent);

            assertNull(badao.getBookingAgentFromAgent(bookingAgent.getAgent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}