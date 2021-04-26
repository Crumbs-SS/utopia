package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.*;
import com.ss.utopia.entity.*;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingAgentDAOTest {
    ConnectionUtil connUtil = new ConnectionUtil();

    @Test
    void addBookingAgent() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BookingAgentDAO badao = new BookingAgentDAO(conn);
            BookingDAO bookingDAO = new BookingDAO(conn);
            UserDAO udao = new UserDAO(conn);

            User user = udao.getAllUsers().get(0);
            Booking booking = bookingDAO.getAllBookings().get(0);

            BookingAgent bookingAgent = new BookingAgent(booking, user);
            badao.addBookingAgent(bookingAgent);

            assertEquals(bookingAgent.getBooking().getId(), badao.getBookingAgentFromAgent(user).getBooking().getId());
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally{
            conn.close();
        }
    }

    @Test
    void deleteBookingAgent() throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BookingAgentDAO badao = new BookingAgentDAO(conn);

            List<BookingAgent> bookingAgents = badao.getAllBookingAgents();
            BookingAgent bookingAgent = bookingAgents.get(bookingAgents.size() - 1);

            badao.deleteBookingAgent(bookingAgent);

            assertNull(badao.getBookingAgentFromAgent(bookingAgent.getAgent()));
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally{
            conn.close();
        }
    }

}