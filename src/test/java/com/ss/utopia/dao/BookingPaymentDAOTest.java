package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingPayment;
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
class BookingPaymentDAOTest {

    @Autowired
    BookingPaymentDAO bpdao;

    @Autowired
    BookingDAO bdao;

    @Test
    @Order(1)
    void addBookingPayment() throws SQLException, ClassNotFoundException {
        Booking booking = bdao.getAllBookings().get(bdao.getAllBookings().size() - 1);
        BookingPayment bookingPayment = new BookingPayment("STRIPE-1", false);
        bookingPayment.setBooking(booking);

        bpdao.addBookingPayment(bookingPayment);
        assertEquals(bookingPayment.getStripeId(), bpdao.getBookingByStripeId("STRIPE-1").getStripeId());
    }

    @Test
    @Order(2)
    void updateBookingPayment() throws SQLException, ClassNotFoundException {
        BookingPayment bookingPayment = bpdao.getBookingByStripeId("STRIPE-1");
        bookingPayment.setRefunded(true);
        bpdao.updateBookingPayment(bookingPayment);

        assertEquals(bookingPayment.getRefunded(), bpdao.getBookingByStripeId("STRIPE-1").getRefunded());
    }

    @Test
    @Order(3)
    void deleteBookingPayment() throws SQLException, ClassNotFoundException {
        List<BookingPayment> bookingPayments = bpdao.getAllBookingPayments();
        BookingPayment bookingPayment = bookingPayments.get(bookingPayments.size() - 1);

        bpdao.deleteBookingPayment(bookingPayment);
        assertNull(bpdao.getBookingByStripeId(bookingPayment.getStripeId()));
    }
}