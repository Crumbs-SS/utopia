package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.dao.FlightBookingDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.FlightBooking;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightBookingDAOTest {

    @Test
    void addFlightBooking() throws SQLException {
        try{
            FlightBookingDAO fbdao = new FlightBookingDAO();
            FlightDAO fdao = new FlightDAO();
            BookingDAO bookingDAO = new BookingDAO();

            Flight flight = fdao.getAllFlights().get(fdao.getAllFlights().size() - 1);
            flight = fdao.getFlightFromId(flight.getId());

            Booking booking = bookingDAO.getAllBookings().get(bookingDAO.getAllBookings().size() - 1);
            booking = bookingDAO.getBookingById(booking.getId());

            FlightBooking flightBooking = new FlightBooking(flight, booking);
            fbdao.addFlightBooking(flightBooking);

            assertEquals(flightBooking.getFlight().getId(), fbdao.getFlightBookingByBooking(booking).getFlight().getId());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void deleteFlightBooking() throws SQLException {
        try{
            FlightBookingDAO fbdao = new FlightBookingDAO();

            List<FlightBooking> flightBookingList = fbdao.getAllFlightBookings();
            FlightBooking flightBooking = flightBookingList.get(flightBookingList.size() - 1);

            fbdao.deleteFlightBooking(flightBooking);
            assertNull(fbdao.getFlightBookingByBooking(flightBooking.getBooking()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}