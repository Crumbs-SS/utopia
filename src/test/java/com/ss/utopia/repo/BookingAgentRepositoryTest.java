package com.ss.utopia.repo;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingAgent;
import com.ss.utopia.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookingAgentRepositoryTest {
    @Autowired  BookingAgentRepository bookingAgentRepository;
    @Autowired  BookingRepository bookingRepository;
    @Autowired  UserRepository userRepository;
    @Autowired  UserRoleRepository userRoleRepository;

    BookingAgent bookingAgent;
    User user;
    Booking booking;

    @BeforeEach
    void createInstances(){
        booking = new Booking(false, "TEST-BOOKING");
        user = new User("TEST",
                "USER",
                "TEST-USER",
                "TESTUSER@GMAIL.COM",
                "TESTING",
                "111"
        );
        user.setUserRole(userRoleRepository.getOne(1));
        bookingAgent = new BookingAgent(booking, user);

        bookingRepository.save(booking);
        userRepository.save(user);

        bookingAgentRepository.save(bookingAgent);
    }

    @AfterEach
    void deleteInstances(){
        bookingRepository.delete(booking);
        userRepository.delete(user);
    }

    @Test
    void getBookingAgentFromAgent() {
        BookingAgent bookingAgentFound = bookingAgentRepository.getBookingAgentFromAgent(user);
        assertEquals(bookingAgentFound.getBooking().getId(), bookingAgent.getBooking().getId());
    }

    @Test
    void getBookingAgentFromBooking() {
        BookingAgent bookingAgentFound = bookingAgentRepository.getBookingAgentFromBooking(booking);
        assertEquals(bookingAgentFound.getBooking().getId(), bookingAgent.getBooking().getId());
    }
}