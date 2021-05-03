package com.ss.utopia.service;

import com.ss.utopia.entity.*;
import com.ss.utopia.repo.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AdminServiceTest {
    @Autowired AdminService adminService;

    @MockBean AirportRepository airportRepository;
    @MockBean FlightRepository flightRepository;
    @MockBean AirplaneRepository airplaneRepository;
    @MockBean RouteRepository routeRepository;
    @MockBean SeatRepository seatRepository;
    @MockBean UserRepository userRepository;
    @MockBean BookingRepository bookingRepository;
    @MockBean BookingPaymentRepository bookingPaymentRepository;
    @MockBean FlightBookingRepository flightBookingRepository;
    @MockBean PassengerRepository passengerRepository;
    @MockBean BookingUserRepository bookingUserRepository;
    @MockBean BookingAgentRepository bookingAgentRepository;
    @MockBean BookingGuestRepository bookingGuestRepository;

    @Test
    void getAirports() {
        Mockito.when(airportRepository.findAll())
                .thenReturn(MockUtil.getMockAirports());

        assertEquals(MockUtil.getMockAirports().size(), adminService.getAirports().size());
    }

    @Test
    void getFlights() {
        Mockito.when(flightRepository.findAll())
                .thenReturn(MockUtil.getMockFlights());

        assertEquals(MockUtil.getMockFlights().size(), adminService.getFlights().size());
    }

    @Test
    void getSeats() {
        Mockito.when(seatRepository.findAll())
                .thenReturn(MockUtil.getMockSeats());

        assertEquals(MockUtil.getMockSeats().size(), adminService.getSeats().size());
    }

    @Test
    void getTravelers() {
        Mockito.when(userRepository.getTravellers())
                .thenReturn(MockUtil.getTravelers());

        assertEquals(MockUtil.getTravelers().size(), adminService.getTravelers().size());
    }

    @Test
    void getEmployees() {
        Mockito.when(userRepository.getEmployees())
                .thenReturn(MockUtil.getEmployees());

        assertEquals(MockUtil.getEmployees().size(), adminService.getEmployees().size());
    }

    @Test
    void getBookings() {
        Mockito.when(bookingRepository.findAll())
                .thenReturn(MockUtil.getBookings());

        assertEquals(MockUtil.getBookings().size(), adminService.getBookings().size());
    }

    @Test
    void getPassengers() {
        Mockito.when(passengerRepository.findAll())
                .thenReturn(MockUtil.getPassengers());

        assertEquals(MockUtil.getPassengers().size(), adminService.getPassengers().size());
    }

    @Test
    void getPassengerById() {
        Optional<Passenger> passenger = MockUtil.getPassengerOptional();
        Mockito.when(passengerRepository.findById(0))
                .thenReturn(passenger);

        assertEquals(passenger.get(),
                adminService.getPassengerById(0));
    }

    @Test
    void deleteAirport() {
        Mockito.doNothing().when(airportRepository).deleteById("0");
        assertNotNull(adminService.deleteAirport("0"));
    }

    @Test
    void deleteFlight() {
        Mockito.doNothing().when(flightRepository).deleteById(0);
        assertNotNull(adminService.deleteFlight(0));
    }
/*
    @Test
    void deleteSeats() {
        Mockito.doNothing().when(seatRepository).deleteById(0);
        assertNotNull(adminService.deleteSeats(0));
    }
*/
    @Test
    void deleteEmployee() {
        Mockito.doNothing().when(userRepository).deleteEmployee(0);
        assertNotNull(adminService.deleteEmployee(0));
    }

    @Test
    void deleteTraveler() {
        Mockito.doNothing().when(userRepository).deleteTraveler(0);
        assertNotNull(adminService.deleteTraveler(0));
    }


    @Test
    void updateFlight() {
        Optional<Flight> flightOptional = MockUtil.getFlightOptional();
        Flight flight = flightOptional.get();

        Mockito.when(flightRepository.findById(1))
                .thenReturn(flightOptional);
        Mockito.when(airplaneRepository.findById(null)).thenReturn(Optional.of(new Airplane()));
        Mockito.when(airportRepository.findById(null)).thenReturn(Optional.of(new Airport()));
        Mockito.when(routeRepository.findById(null)).thenReturn(Optional.of(new Route()));
        Mockito.when(flightRepository.save(flight))
                .thenReturn(flight);

        assertEquals(flight, adminService.updateFlight(1, flight));
    }

    @Test
    void updateSeats() {
        Optional<Seats> seatsOptional = MockUtil.getSeatOptional();
        Seats seats = seatsOptional.get();

        Mockito.when(seatRepository.findById(0))
                .thenReturn(seatsOptional);
        Mockito.when(seatRepository.save(seats))
                .thenReturn(seats);

        assertEquals(seats, adminService.updateSeats(0, seats));
    }

    @Test
    void updateAirport() {
        Optional<Airport> airportOptional = MockUtil.getAirportOptional();
        Airport airport = airportOptional.get();

        Mockito.when(airportRepository.findById(airport.getAirportCode()))
                .thenReturn(airportOptional);
        Mockito.when(airportRepository.save(airport))
                .thenReturn(airport);

        assertEquals(airport, adminService.updateAirport(airport.getAirportCode(), airport));
    }

    @Test
    void updateTraveler() {
        Optional<User> travelerOptional = MockUtil.getUserOptional();
        User traveler = travelerOptional.get();
        Mockito.when(userRepository.findById(0))
                .thenReturn(travelerOptional);
        Mockito.when(userRepository.save(traveler))
                .thenReturn(traveler);

        assertEquals(traveler, adminService.updateTraveler(0, traveler));
    }

    @Test
    void updateEmployee() {
        Optional<User> employeeOptional = MockUtil.getUserOptional();
        User employee = employeeOptional.get();
        Mockito.when(userRepository.findById(0))
                .thenReturn(employeeOptional);
        Mockito.when(userRepository.save(employee))
                .thenReturn(employee);

        assertEquals(employee, adminService.updateEmployee(0, employee));
    }

    @Test
    void updatePassenger() {
        Optional<Passenger> passengerOptional = MockUtil.getPassengerOptional();
        Passenger passenger = passengerOptional.get();
        Mockito.when(passengerRepository.findById(0))
                .thenReturn(passengerOptional);
        Mockito.when(passengerRepository.save(passenger))
                .thenReturn(passenger);

        assertEquals(passenger, adminService.updatePassenger(0, passenger));
    }

    @Test
    void cancelBooking() {
        Optional<Booking> bookingOptional = MockUtil.getBookingOptional();
        Optional<BookingPayment> bookingPaymentOptional = MockUtil.getBookingPaymentOptional();
        Booking booking = bookingOptional.get();
        BookingPayment bookingPayment = bookingPaymentOptional.get();

        Mockito.when(bookingRepository.findById(1)).
                thenReturn(bookingOptional);
        Mockito.when(bookingPaymentRepository.findById(1)).
                thenReturn(bookingPaymentOptional);
        Mockito.when(bookingRepository.save(booking)).
                thenReturn(booking);

        assertNotEquals(booking.getActive(), adminService.cancelBooking(1).getActive());
    }

    @Test
    void uncancelBooking() {
        Optional<Booking> cancelledBookingOptional = MockUtil.getCancelledBookingOptional();
        Optional<BookingPayment> bookingPaymentOptional = MockUtil.getBookingPaymentOptional();
        Booking cancelledBooking = cancelledBookingOptional.get();
        BookingPayment bookingPayment = bookingPaymentOptional.get();

        Mockito.when(bookingRepository.findById(1)).
                thenReturn(cancelledBookingOptional);
        Mockito.when(bookingPaymentRepository.findById(1)).
                thenReturn(bookingPaymentOptional);
        Mockito.when(bookingRepository.save(cancelledBooking)).
                thenReturn(cancelledBooking);


        assertNotEquals(cancelledBooking.getActive(), adminService.uncancelBooking(1).getActive());
    }

}