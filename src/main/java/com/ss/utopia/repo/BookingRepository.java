package com.ss.utopia.repo;

import com.ss.utopia.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT b FROM booking b WHERE b.confirmationCode = ?1")
    List<Booking> getBookingsByConfirmationCode(String confirmationCode);
}
