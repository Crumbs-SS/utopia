package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingGuest;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingGuestDAO extends BaseDAO implements ResultSetExtractor<List<BookingGuest>> {

    public void addBookingGuest(BookingGuest bookingGuest){
        jdbcTemplate.update("INSERT INTO booking_guest(booking_id, contact_email, contact_phone) VALUES (?, ?, ?)",
                bookingGuest.getBooking().getId(),
                bookingGuest.getContactEmail(),
                bookingGuest.getContactPhone());
    }

    public void deleteBookingGuest(BookingGuest bookingGuest){
        jdbcTemplate.update("DELETE FROM booking_guest WHERE booking_id = ?", bookingGuest.getBooking().getId());
    }

    public BookingGuest getBookingGuestByEmail(String email) {
        List<BookingGuest> bookingGuests = jdbcTemplate.query("SELECT * FROM booking_guest WHERE contact_email = ?", new Object[]{
           email}, this);

        if(bookingGuests != null && bookingGuests.size() > 0)
            return bookingGuests.get(0);

        return null;
    }

    public BookingGuest getBookingGuestByBooking(Booking booking) {
        List<BookingGuest> bookingGuests = jdbcTemplate.query("SELECT * FROM booking_guest WHERE booking_id = ?", new Object[]{
                booking.getId()}, this);

        if(bookingGuests != null && bookingGuests.size() > 0)
            return bookingGuests.get(0);

        return null;
    }


    @Override
    public List<BookingGuest> extractData(ResultSet rs) throws SQLException {
        List<BookingGuest> bookingGuests = new ArrayList<>();

        while(rs.next())
            bookingGuests.add(BookingGuest.toObject(rs));
        return bookingGuests;
    }
}
