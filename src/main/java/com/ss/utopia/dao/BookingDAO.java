package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO extends BaseDAO implements ResultSetExtractor<List<Booking>> {

    public Integer addBooking(Booking booking){
        return jdbcTemplate.update("INSERT INTO booking(is_active, confirmation_code) VALUES(?, ?)", booking.getIsActive(),
                booking.getConfirmationCode());
    }

    public void updateBooking(Booking booking) {
        jdbcTemplate.update("UPDATE booking SET is_active = ?, confirmation_code = ? WHERE booking.id = ?", booking.getIsActive(),
                booking.getConfirmationCode(),
                booking.getId());
    }

    public void deleteBooking(Booking booking) {
        jdbcTemplate.update("DELETE FROM booking WHERE id = ?", booking.getId());
    }

    public Booking getBookingById(Integer id){
        List<Booking> bookings = jdbcTemplate.query("SELECT * FROM booking WHERE id = ?", new Object[]{id},this);
        if(bookings != null && bookings.size() > 0)
            return bookings.get(0);

        return null;
    }

    public Booking getBookingByCode(String confirmationCode){
        List<Booking> bookings = jdbcTemplate.query("SELECT * FROM booking WHERE confirmation_code = ?", new Object[]{confirmationCode},this);
        if(bookings != null && bookings.size() > 0)
            return bookings.get(0);

        return null;
    }

    public List<Booking> getAllBookings() {
        return jdbcTemplate.query("SELECT * FROM booking", this);
    }

    @Override
    public List<Booking> extractData(ResultSet rs) throws SQLException {
        List<Booking> bookings = new ArrayList<>();

        while(rs.next())
            bookings.add(Booking.toObject(rs));

        return bookings;
    }
}
