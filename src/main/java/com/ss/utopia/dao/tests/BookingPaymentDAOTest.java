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

    @Test
    void addBookingPayment() throws SQLException {
        try {
            BookingPaymentDAO bpdao = new BookingPaymentDAO();
            BookingDAO bdao = new BookingDAO();

            Booking booking = bdao.getAllBookings().get(bdao.getAllBookings().size() - 1);
            BookingPayment bookingPayment = new BookingPayment("STRIPE-1", false);
            bookingPayment.setBooking(booking);

            bpdao.addBookingPayment(bookingPayment);

            assertEquals(bookingPayment.getStripeId(), bpdao.getBookingByStripeId("STRIPE-1").getStripeId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateBookingPayment() throws SQLException {
        try {
            BookingPaymentDAO bpdao = new BookingPaymentDAO();

            BookingPayment bookingPayment = bpdao.getBookingByStripeId("STRIPE-1");
            bookingPayment.setRefunded(true);
            bpdao.updateBookingPayment(bookingPayment);

            assertEquals(bookingPayment.getRefunded(), bpdao.getBookingByStripeId("STRIPE-1").getRefunded());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteBookingPayment() throws SQLException, ClassNotFoundException {
        BookingPaymentDAO bpdao = new BookingPaymentDAO();

        List<BookingPayment> bookingPayments = bpdao.getAllBookingPayments();
        BookingPayment bookingPayment = bookingPayments.get(bookingPayments.size() - 1);
        bpdao.deleteBookingPayment(bookingPayment);
        assertNull(bpdao.getBookingByStripeId(bookingPayment.getStripeId()));
    }
}