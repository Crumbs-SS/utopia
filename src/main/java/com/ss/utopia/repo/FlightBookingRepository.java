package com.ss.utopia.repo;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.FlightBooking;
import com.ss.utopia.entity.FlightBookingID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, FlightBookingID> {

    @Query("SELECT fb FROM flight_bookings fb WHERE fb.flight = ?1")
    List<FlightBooking> findByFlight(Flight flight);

    @Query("SELECT fb FROM flight_bookings fb WHERE fb.booking = ?1")
    List<FlightBooking> findByBooking(Booking booking);

    @Query("SELECT fb FROM flight_bookings fb WHERE fb.booking = ?1 AND fb.flight= ?2")
    List<FlightBooking> findByBookingAndFlight(Booking booking, Flight flight);
}
