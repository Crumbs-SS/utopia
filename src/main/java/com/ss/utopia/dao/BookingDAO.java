package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO extends BaseDAO<Booking>{

    public BookingDAO(Connection conn) {
        super(conn);
    }

    public Integer addBooking(Booking booking)throws SQLException, ClassNotFoundException{
        Integer key = saveWithPK("INSERT INTO booking(is_active, confirmation_code) VALUES(?, ?)", new Object[]{
                booking.getIsActive(),
                booking.getConfirmationCode()
        });
        booking.setId(key);
        return key;
    }

    public void updateBooking(Booking booking) throws SQLException, ClassNotFoundException{
        save("UPDATE booking SET is_active = ?, confirmation_code = ? WHERE booking.id = ?", new Object[]{
                booking.getIsActive(),
                booking.getConfirmationCode(),
                booking.getId()
        });
    }

    public void deleteBooking(Booking booking) throws SQLException, ClassNotFoundException{
        save("DELETE FROM booking WHERE id = ?", new Object[]{booking.getId()});
    }

    public Booking getBookingById(Integer id) throws SQLException, ClassNotFoundException{
        List<Booking> bookings = read("SELECT * FROM booking WHERE id = ?", new Object[]{id});
        if(!bookings.isEmpty())
            return bookings.get(0);

        return null;
    }

    public Booking getBookingByCode(String confirmationCode) throws SQLException, ClassNotFoundException{
        List<Booking> bookings = read("SELECT * FROM booking WHERE confirmation_code = ?", new Object[]{confirmationCode});
        if(!bookings.isEmpty())
            return bookings.get(0);

        return null;
    }

    public List<Booking> getAllBookings() throws SQLException, ClassNotFoundException{
        return read("SELECT * FROM booking", null);
    }

    @Override
    public List<Booking> extractData(ResultSet rs) throws SQLException {
        List<Booking> bookings = new ArrayList<>();

        while(rs.next())
            bookings.add(Booking.toObject(rs));

        return bookings;
    }
}
