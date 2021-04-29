package com.ss.utopia.service;

import com.ss.utopia.entity.*;
import com.ss.utopia.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = { Exception.class })
public class TravelerService {
    @Autowired AirplaneRepository airplaneRepository;
    @Autowired AirplaneTypeRepository airplaneTypeRepository;
    @Autowired RouteRepository routeRepository;
    @Autowired AirportRepository airportRepository;
    @Autowired UserRoleRepository userRoleRepository;
    @Autowired UserRepository userRepository;
    @Autowired FlightRepository flightRepository;
    @Autowired BookingRepository bookingRepository;
    @Autowired FlightBookingRepository flightBookingRepository;
    @Autowired PassengerRepository passengerRepository;
    @Autowired BookingPaymentRepository bookingPaymentRepository;


    public List<AirplaneType> getAllAirplaneTypes(){
        return airplaneTypeRepository.findAll();
    }

    public List<Airplane> getAirplanesByCapacity(String maxCapacity) {
        return airplaneRepository.findAirplanesByMaxCapacity(Integer.parseInt(maxCapacity));
    }

    public List<Airplane> getAirplanes(){
        return airplaneRepository.findAll();
    }

    public List<Route> getRoutes(){
        return routeRepository.findAll();
    }

    public List<Airport> getAirports(){
        return airportRepository.findAll();
    }

    public Airport getAirport(String airportCode){
        return airportRepository.findById(airportCode).get();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<UserRole> getAllUserRoles(){
        return  userRoleRepository.findAll();
    }

    public User authenticateUser(User user){
        return userRepository.authenticateUser(user.getUsername(), user.getPassword());
    }

    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    public Flight getFlight(Integer id){
        return flightRepository.getOne(id);
    }

    public Booking getBooking(String confirmationCode){
        return bookingRepository.getBookingByConfirmationCode(confirmationCode);
    }

    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    public FlightBooking getFlightBookingByFlight(String flightId){
       List<FlightBooking> flightBookings = flightBookingRepository.
               findByFlight(flightRepository.findById(Integer.parseInt(flightId)).get());
       if (!flightBookings.isEmpty())
           return flightBookings.get(0);
       return null;
    }

    public FlightBooking getFlightBookingByBooking(String bookingId){
        List<FlightBooking> flightBookings = flightBookingRepository
                .findByBooking(bookingRepository.findById(Integer.parseInt(bookingId)).get());
        if (!flightBookings.isEmpty())
            return flightBookings.get(0);
        return null;
    }

    public List<FlightBooking> getAllFlightBookings(){
        return flightBookingRepository.findAll();
    }

    public List<Passenger> getPassengersByBooking(){
        return passengerRepository.getPassengersByBooking(bookingRepository.findById(1).get());
    }

    public List<BookingPayment> getAllBookingPayments(){
        return bookingPaymentRepository.findAll();
    }

    public BookingPayment getBookingPaymentsByStripeId(String stripeId){
        return bookingPaymentRepository.getBookingByStripeId(stripeId);
    }

    public BookingPayment getBookingPaymentsByBooking(Booking booking){
        return bookingPaymentRepository.getBookingPaymentByBooking(booking);
    }
}
