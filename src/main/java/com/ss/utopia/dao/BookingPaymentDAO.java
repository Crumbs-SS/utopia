package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingPayment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingPaymentDAO extends BaseDAO<BookingPayment> {
    public BookingPaymentDAO(Connection conn) {
        super(conn);
    }

    public void addBookingPayment(BookingPayment bookingPayment) throws SQLException, ClassNotFoundException{
        save("INSERT INTO booking_payment(booking_id, stripe_id, refunded) VALUES (?, ?, ?)", new Object[]{
                bookingPayment.getBooking().getId(),
                bookingPayment.getStripeId(),
                bookingPayment.getRefunded()
        });
    }

    public void updateBookingPayment(BookingPayment bookingPayment) throws SQLException, ClassNotFoundException{
        save("UPDATE booking_payment SET stripe_id = ?, refunded = ? WHERE booking_id = ?", new Object[]{
           bookingPayment.getStripeId(),
           bookingPayment.getRefunded(),
           bookingPayment.getBooking().getId()
        });
    }

    public void deleteBookingPayment(BookingPayment bookingPayment) throws SQLException, ClassNotFoundException{
        save("DELETE FROM booking_payment WHERE booking_id = ?", new Object[]{
                3
        });
    }

    public BookingPayment getBookingPaymentByBooking(Booking booking) throws SQLException, ClassNotFoundException{
        List<BookingPayment> bookingPayments = read("SELECT * FROM booking_payment WHERE booking_id = ?", new Object[]{
                booking.getId()
        });

        if(!bookingPayments.isEmpty())
            return bookingPayments.get(0);

        return null;
    }

    public BookingPayment getBookingByStripeId(String stripeId) throws SQLException, ClassNotFoundException{
        List<BookingPayment> bookingPayments = read("SELECT * FROM booking_payment WHERE stripe_id = ?", new Object[]{
                stripeId
        });

        if(!bookingPayments.isEmpty())
            return bookingPayments.get(0);

        return null;
    }

    public List<BookingPayment> getAllBookingPayments() throws SQLException, ClassNotFoundException{
        return read("SELECT * FROM booking_payment", null);
    }

    @Override
    public List<BookingPayment> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<BookingPayment> bookingPayments = new ArrayList<>();

        while(rs.next())
            bookingPayments.add(BookingPayment.toObject(rs));

        return bookingPayments;
    }
}
