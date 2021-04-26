package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.FlightBooking;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightBookingDAO extends BaseDAO implements ResultSetExtractor<List<FlightBooking>> {

    public void addFlightBooking(FlightBooking flightBooking) {
        jdbcTemplate.update("INSERT INTO flight_bookings(flight_id, booking_id) VALUES (?,?)", flightBooking.getFlight().getId(),
                flightBooking.getBooking().getId());
    }

   public void deleteFlightBooking(FlightBooking flightBooking){
        jdbcTemplate.update("DELETE FROM flight_bookings WHERE booking_id = ?", flightBooking.getBooking().getId());
   }

   public List<FlightBooking> getAllFlightBookings() {
        return jdbcTemplate.query("SELECT * FROM flight_bookings", this);
   }

   public FlightBooking getFlightBookingByBooking(Booking booking) {
        List<FlightBooking> flightBookings = jdbcTemplate.query("SELECT * FROM flight_bookings WHERE booking_id = ?", new Object[]{
           booking.getId()},this);

        if(flightBookings != null && flightBookings.size() > 0)
            return flightBookings.get(0);

        return null;
   }

    @Override
    public List<FlightBooking> extractData(ResultSet rs) throws SQLException {
        List<FlightBooking> flightBookings = new ArrayList<>();
        while(rs.next())
            flightBookings.add(FlightBooking.toObject(rs));

        return flightBookings;
    }
}
