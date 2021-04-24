package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.dao.FlightBookingDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.FlightBooking;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightBookingDAOTest {
    ConnectionUtil connUtil = new ConnectionUtil();

    @Test
    void addFlightBooking() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            FlightBookingDAO fbdao = new FlightBookingDAO(conn);
            FlightDAO fdao = new FlightDAO(conn);
            BookingDAO bookingDAO = new BookingDAO(conn);

            Flight flight = fdao.getAllFlights().get(fdao.getAllFlights().size() - 1);
            flight = fdao.getFlightFromId(flight.getId());

            Booking booking = bookingDAO.getAllBookings().get(bookingDAO.getAllBookings().size() - 1);
            booking = bookingDAO.getBookingById(booking.getId());

            FlightBooking flightBooking = new FlightBooking(flight, booking);
            fbdao.addFlightBooking(flightBooking);

            assertEquals(flightBooking.getFlight().getId(), fbdao.getFlightBookingByBooking(booking).getFlight().getId());
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.commit();
        }finally{
            conn.close();
        }
    }

    @Test
    void deleteFlightBooking() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            FlightBookingDAO fbdao = new FlightBookingDAO(conn);

            List<FlightBooking> flightBookingList = fbdao.getAllFlightBookings();
            FlightBooking flightBooking = flightBookingList.get(flightBookingList.size() - 1);

            fbdao.deleteFlightBooking(flightBooking);
            assertNull(fbdao.getFlightBookingByBooking(flightBooking.getBooking()));
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.commit();
        }finally{
            conn.close();
        }
    }
}