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

//    public FlightBooking getFlightBooking(Flight flight){
////       List<FlightBooking>
//    }

    public FlightBooking getFlightBooking(Booking booking){
        return flightBookingRepository.findByBooking(booking).get(0);
    }

    public FlightBooking getFlightBooking(Flight flight, Booking booking){
        return flightBookingRepository.findByBookingAndFlight(booking, flight);
    }


}
