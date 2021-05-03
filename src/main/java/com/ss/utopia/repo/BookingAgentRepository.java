package com.ss.utopia.repo;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingAgent;
import com.ss.utopia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingAgentRepository extends JpaRepository<BookingAgent, Integer> {

    @Query("SELECT ba FROM booking_agent ba WHERE ba.agent = ?1")
    BookingAgent getBookingAgentFromAgent(User user);

    @Query("SELECT ba FROM booking_agent ba WHERE ba.booking = ?1")
    BookingAgent getBookingAgentFromBooking(Booking booking);
}
