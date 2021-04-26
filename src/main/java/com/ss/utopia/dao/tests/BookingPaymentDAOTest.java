package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.dao.BookingPaymentDAO;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingPayment;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingPaymentDAOTest {
    ConnectionUtil connUtil = new ConnectionUtil();

    @Test
    void addBookingPayment() throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BookingPaymentDAO bpdao = new BookingPaymentDAO(conn);
            BookingDAO bdao = new BookingDAO(conn);

            Booking booking = bdao.getAllBookings().get(bdao.getAllBookings().size() - 1);
            BookingPayment bookingPayment = new BookingPayment("STRIPE-1", false);
            bookingPayment.setBooking(booking);

            bpdao.addBookingPayment(bookingPayment);

            assertEquals(bookingPayment.getStripeId(), bpdao.getBookingByStripeId("STRIPE-1").getStripeId());
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    @Test
    void updateBookingPayment() throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BookingPaymentDAO bpdao = new BookingPaymentDAO(conn);

            BookingPayment bookingPayment = bpdao.getBookingByStripeId("STRIPE-1");
            bookingPayment.setRefunded(true);
            bpdao.updateBookingPayment(bookingPayment);

            conn.commit();
            assertEquals(bookingPayment.getRefunded(), bpdao.getBookingByStripeId("STRIPE-1").getRefunded());
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    @Test
    void deleteBookingPayment() throws SQLException, ClassNotFoundException {
        ConnectionUtil connUtil = new ConnectionUtil();
        Connection conn = connUtil.getConnection();
        BookingPaymentDAO bpdao = new BookingPaymentDAO(conn);

        List<BookingPayment> bookingPayments = bpdao.getAllBookingPayments();
        BookingPayment bookingPayment = bookingPayments.get(bookingPayments.size() - 1);

        bpdao.deleteBookingPayment(bookingPayment);

        assertNull(bpdao.getBookingByStripeId(bookingPayment.getStripeId()));

        conn.commit();
        conn.close();
    }
}