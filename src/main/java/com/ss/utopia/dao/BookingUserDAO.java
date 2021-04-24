package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingUser;
import com.ss.utopia.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingUserDAO extends BaseDAO<BookingUser> {


    public BookingUserDAO(Connection conn) {
        super(conn);
    }

    public void addBookingUser(BookingUser bookingUser) throws SQLException, ClassNotFoundException{
            save("INSERT INTO booking_user(booking_id, user_id) VALUES (?, ?)", new Object[]{
                    bookingUser.getBooking().getId(),
                    bookingUser.getUser().getId()
            });
        }

        public void deleteBookingUser(BookingUser bookingUser) throws SQLException, ClassNotFoundException{
            save("DELETE FROM booking_user WHERE booking_id = ?", new Object[]{
                    bookingUser.getBooking().getId()
            });
        }

        public BookingUser getBookingUserFromUser(User user) throws SQLException, ClassNotFoundException{
            List<BookingUser> bookingUsers = read("SELECT * FROM booking_user WHERE user_id = ?", new Object[]{
                    user.getId()
            });

            if(!bookingUsers.isEmpty())
                return bookingUsers.get(0);
            return null;
        }

        public BookingUser getBookingUserFromBooking(Booking booking) throws SQLException, ClassNotFoundException{
            List<BookingUser> bookingUsers = read("SELECT * FROM booking_user WHERE booking_id = ?", new Object[]{
                    booking.getId()
            });

            if(!bookingUsers.isEmpty())
                return bookingUsers.get(0);
            return null;
        }

        public List<BookingUser> getAllBookingUsers() throws SQLException, ClassNotFoundException{
            return read("SELECT * FROM booking_user", null);
        }

        @Override
        public List<BookingUser> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
            List<BookingUser> bookingUsers = new ArrayList<>();

            while(rs.next())
                bookingUsers.add(BookingUser.toObject(rs));

            return bookingUsers;
        }
    }
