package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingGuest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingGuestDAO extends BaseDAO<BookingGuest> {
    public BookingGuestDAO(Connection conn) {
        super(conn);
    }

    public void addBookingGuest(BookingGuest bookingGuest) throws SQLException, ClassNotFoundException {
        save("INSERT INTO booking_guest(booking_id, contact_email, contact_phone) VALUES (?, ?, ?)",
                new Object[]{
                   bookingGuest.getBooking().getId(),
                   bookingGuest.getContactEmail(),
                   bookingGuest.getContactPhone()
                });
    }

    public void deleteBookingGuest(BookingGuest bookingGuest) throws SQLException, ClassNotFoundException{
        save("DELETE FROM booking_guest WHERE booking_id = ?", new Object[]{
                bookingGuest.getBooking().getId()
        });
    }

    public BookingGuest getBookingGuestByEmail(String email) throws SQLException, ClassNotFoundException{
        List<BookingGuest> bookingGuests = read("SELECT * FROM booking_guest WHERE contact_email = ?", new Object[]{
           email
        });

        if(!bookingGuests.isEmpty())
            return bookingGuests.get(0);
        return null;
    }

    public BookingGuest getBookingGuestByBooking(Booking booking) throws SQLException, ClassNotFoundException{
        List<BookingGuest> bookingGuests = read("SELECT * FROM booking_guest WHERE booking_id = ?", new Object[]{
                booking.getId()
        });

        if(!bookingGuests.isEmpty())
            return bookingGuests.get(0);
        return null;
    }


    @Override
    public List<BookingGuest> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<BookingGuest> bookingGuests = new ArrayList<>();

        while(rs.next())
            bookingGuests.add(BookingGuest.toObject(rs));
        return bookingGuests;
    }
}
