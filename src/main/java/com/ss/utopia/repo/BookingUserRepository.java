package com.ss.utopia.repo;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingUserRepository extends JpaRepository<BookingUser, Integer> {

    @Query("SELECT bu FROM booking_user bu WHERE bu.booking = ?1")
    BookingUser getBookingUserFromBooking(Booking booking);
}
