package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.FlightBooking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightBookingDAO extends BaseDAO<FlightBooking> {
    public FlightBookingDAO(Connection conn) {
        super(conn);
    }

    public void addFlightBooking(FlightBooking flightBooking) throws SQLException, ClassNotFoundException {
        save("INSERT INTO flight_bookings(flight_id, booking_id) VALUES (?,?)", new Object[]{
           flightBooking.getFlight().getId(),
           flightBooking.getBooking().getId()
        });
    }

   public void deleteFlightBooking(FlightBooking flightBooking) throws SQLException, ClassNotFoundException{
        save("DELETE FROM flight_bookings WHERE booking_id = ?", new Object[]{
                flightBooking.getBooking().getId()
        });
   }

   public List<FlightBooking> getAllFlightBookings() throws SQLException, ClassNotFoundException{
        return read("SELECT * FROM flight_bookings", null);
   }

   public FlightBooking getFlightBookingByBooking(Booking booking) throws SQLException, ClassNotFoundException{
        List<FlightBooking> flightBookings = read("SELECT * FROM flight_bookings WHERE booking_id = ?", new Object[]{
           booking.getId()
        });

        if(!flightBookings.isEmpty())
            return flightBookings.get(0);

        return null;
   }

    @Override
    public List<FlightBooking> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<FlightBooking> flightBookings = new ArrayList<>();
        while(rs.next())
            flightBookings.add(FlightBooking.toObject(rs));

        return flightBookings;
    }
}
