package com.ss.utopia.service;

import com.ss.utopia.entity.*;
import com.ss.utopia.repo.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TravelerServiceTest {

    @Autowired TravelerService travelerService;

    @MockBean FlightRepository flightRepository;
    @MockBean AirplaneRepository airplaneRepository;
    @MockBean RouteRepository routeRepository;
    @MockBean UserRepository userRepository;
    @MockBean BookingRepository bookingRepository;
    @MockBean BookingUserRepository bookingUserRepository;
    @MockBean BookingPaymentRepository bookingPaymentRepository;
    @MockBean FlightBookingRepository flightBookingRepository;
    @MockBean PassengerRepository passengerRepository;

    @Test
    void getFlights() {
        Mockito.when(flightRepository.findAll())
                .thenReturn(MockUtil.getMockFlights());

        assertEquals(3, travelerService.getFlights().size());
    }

    @Test
    void login() {
        User user = MockUtil.getUser();
        String username = user.getUsername();
        String password = user.getPassword();
        Mockito.when(userRepository.authenticateUser(username, password))
                .thenReturn(MockUtil.authenticateUser(username, password));

        assertEquals("TESTGUY@GMAIL",
                travelerService.login(user).getEmail());
    }

//    @Test
//    void addBooking() {
//        Booking booking = MockUtil.getBooking();
//        BookingPayment bookingPayment = MockUtil.getBookingPayment();
//        BookingUser bookingUser = MockUtil.getBookingUser();
//        FlightBooking flightBooking = MockUtil.getFlightBooking();
//
//        Mockito.when(bookingRepository.findAll()).thenReturn(MockUtil.getBookings());
//        Mockito.when(bookingRepository.saveAndFlush(booking)).thenReturn(booking);
//        Mockito.when(flightRepository.findById(null)).thenReturn(MockUtil.getFlightOptional());
//        Mockito.when(userRepository.findById(null)).thenReturn(MockUtil.getUserOptional());
//        Mockito.when(bookingPaymentRepository.save(bookingPayment)).thenReturn(bookingPayment);
//        Mockito.when(flightBookingRepository.save(flightBooking)).thenReturn(flightBooking);
//        Mockito.when(bookingUserRepository.save(bookingUser)).thenReturn(bookingUser);
//
//
//        assertEquals(booking.getConfirmationCode(), travelerService.addBooking(MockUtil.getBookingDTO()));
//    }

    @Test
    void cancelBooking() {
        Booking booking = MockUtil.getBooking();
        BookingPayment bookingPayment = MockUtil.getBookingPayment();

        Mockito.when(bookingRepository.findById(0)).thenReturn(MockUtil.getBookingOptional());
        Mockito.when(bookingPaymentRepository.getBookingByStripeId(booking.getConfirmationCode()))
                .thenReturn(bookingPayment);
        Mockito.when(bookingRepository.save(booking)).thenReturn(booking);
        Mockito.when(bookingPaymentRepository.save(bookingPayment)).thenReturn(bookingPayment);

        assertTrue(travelerService.cancelBooking("0"));
    }

    @Test
    void getAllBookings() {
        Mockito.when(bookingRepository.findAll())
                .thenReturn(MockUtil.getBookings());

        assertEquals(3, travelerService.getAllBookings().size());
    }
}