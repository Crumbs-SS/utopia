package com.ss.utopia.repo;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    @Query("SELECT p FROM passenger p WHERE p.booking = ?1")
    List<Passenger> getPassengersByBooking(Booking booking);
}
