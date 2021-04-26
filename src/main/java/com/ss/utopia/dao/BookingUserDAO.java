package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingUser;
import com.ss.utopia.entity.Route;
import com.ss.utopia.entity.User;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingUserDAO extends BaseDAO implements ResultSetExtractor<List<BookingUser>> {

    public void addBookingUser(BookingUser bookingUser) {
            jdbcTemplate.update("INSERT INTO booking_user(booking_id, user_id) VALUES (?, ?)", bookingUser.getBooking().getId(),
                    bookingUser.getUser().getId());
        }

        public void deleteBookingUser(BookingUser bookingUser) {
            jdbcTemplate.update("DELETE FROM booking_user WHERE booking_id = ?", bookingUser.getBooking().getId());
        }

        public BookingUser getBookingUserFromUser(User user) {
            List<BookingUser> bookingUsers = jdbcTemplate.query("SELECT * FROM booking_user WHERE user_id = ?", new Object[]{
                    user.getId()},this);

            if(bookingUsers != null && bookingUsers.size() > 0)
                return bookingUsers.get(0);
            return null;
        }

        public BookingUser getBookingUserFromBooking(Booking booking) {
            List<BookingUser> bookingUsers = jdbcTemplate.query("SELECT * FROM booking_user WHERE booking_id = ?", new Object[]{
                    booking.getId()},this);

            if(bookingUsers != null && bookingUsers.size() > 0)
                return bookingUsers.get(0);
            return null;
        }

        public List<BookingUser> getAllBookingUsers() {
            return jdbcTemplate.query("SELECT * FROM booking_user", this);
        }

        @Override
        public List<BookingUser> extractData(ResultSet rs) throws SQLException {
            List<BookingUser> bookingUsers = new ArrayList<>();

            while(rs.next())
                bookingUsers.add(BookingUser.toObject(rs));

            return bookingUsers;
        }
    }
