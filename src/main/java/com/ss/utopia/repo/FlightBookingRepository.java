package com.ss.utopia.repo;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.FlightBooking;
import com.ss.utopia.entity.FlightBookingID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, FlightBookingID> {


    List<FlightBooking> findByFlight(Flight flight);

    List<FlightBooking> findByBooking(Booking booking);

    FlightBooking findByBookingAndFlight(Booking booking, Flight flight);
}
