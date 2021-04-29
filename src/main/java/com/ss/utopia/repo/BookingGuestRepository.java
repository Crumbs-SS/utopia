package com.ss.utopia.repo;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingGuestRepository extends JpaRepository<BookingGuest, Integer> {

    @Query("SELECT bg FROM booking_guest bg WHERE bg.contactEmail = ?1")
    BookingGuest getBookingGuestByEmail(String email);

    @Query("SELECT bg FROM booking_guest bg WHERE bg.booking = ?1")
    BookingGuest getBookingGuestByBooking(Booking booking);
}
