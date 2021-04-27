package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingPayment;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class BookingPaymentDAO extends BaseDAO implements ResultSetExtractor<List<BookingPayment>> {

    public void addBookingPayment(BookingPayment bookingPayment) {
        jdbcTemplate.update("INSERT INTO booking_payment(booking_id, stripe_id, refunded) VALUES (?, ?, ?)", bookingPayment.getBooking().getId(),
                bookingPayment.getStripeId(),
                bookingPayment.getRefunded());
    }

    public void updateBookingPayment(BookingPayment bookingPayment){
        jdbcTemplate.update("UPDATE booking_payment SET stripe_id = ?, refunded = ? WHERE booking_id = ?", bookingPayment.getStripeId(),
                bookingPayment.getRefunded(),
                bookingPayment.getBooking().getId());
    }

    public void deleteBookingPayment(BookingPayment bookingPayment) {
        jdbcTemplate.update("DELETE FROM booking_payment WHERE booking_id = ?", bookingPayment.getBooking().getId());
    }

    public BookingPayment getBookingPaymentByBooking(Booking booking) {
        List<BookingPayment> bookingPayments = jdbcTemplate.query("SELECT * FROM booking_payment WHERE booking_id = ?", new Object[]{
                booking.getId()}, this);

        if(bookingPayments != null && bookingPayments.size() > 0)
            return bookingPayments.get(0);

        return null;
    }

    public BookingPayment getBookingByStripeId(String stripeId) {
        List<BookingPayment> bookingPayments = jdbcTemplate.query("SELECT * FROM booking_payment WHERE stripe_id = ?", new Object[]{
                stripeId}, this);

        if(bookingPayments != null && bookingPayments.size() > 0)
            return bookingPayments.get(0);

        return null;
    }

    public List<BookingPayment> getAllBookingPayments() {
        return jdbcTemplate.query("SELECT * FROM booking_payment", this);
    }

    @Override
    public List<BookingPayment> extractData(ResultSet rs) throws SQLException{
        List<BookingPayment> bookingPayments = new ArrayList<>();

        while(rs.next())
            bookingPayments.add(BookingPayment.toObject(rs));

        return bookingPayments;
    }
}
