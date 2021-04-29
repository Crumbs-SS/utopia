package com.ss.utopia.controller;

import com.ss.utopia.entity.*;
import com.ss.utopia.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/traveler")
public class TravelerController {

    @Autowired
    TravelerService travelerService;

    @GetMapping("/planetypes")
    public List<AirplaneType> getAirplaneTypes(){
        return travelerService.getAllAirplaneTypes();
    }

    @GetMapping("/planes/{maxCapacity}")
    public List<Airplane> getAirplaneTypes(@PathVariable String maxCapacity ){
        return travelerService.getAirplanesByCapacity(maxCapacity);
    }

    @GetMapping("/airplanes")
    public List<Airplane> getAirplanes(){
        return travelerService.getAirplanes();
    }

    @GetMapping("/routes")
    public List<Route> getRoutes(){
        return travelerService.getRoutes();
    }

    @GetMapping("/airports")
    public List<Airport> getAirports(){
        return travelerService.getAirports();
    }

    @GetMapping("/airports/{airportCode}")
    public Airport getAirport(@PathVariable String airportCode){
        return travelerService.getAirport(airportCode);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return travelerService.getAllUsers();
    }

    @GetMapping("/user_roles")
    public List<UserRole> getUserRoles(){
        return travelerService.getAllUserRoles();
    }

    @PostMapping("/login")
    public User login(@RequestBody User user){
        return travelerService.authenticateUser(user);
    }

    @GetMapping("/flights")
    public List<Flight> getFlights(){
        return travelerService.getAllFlights();
    }

    @GetMapping("/flights/{id}")
    public Flight getFlight(@PathVariable String id){
        return travelerService.getFlight(Integer.parseInt(id));
    }

    @GetMapping("/bookings/{confirmationCode}")
    public Booking getBooking(@PathVariable String confirmationCode){
        return travelerService.getBooking(confirmationCode);
    }

    @GetMapping("/bookings")
    public List<Booking> getAllBookings(){
        return travelerService.getAllBookings();
    }

    @GetMapping("/flight_bookings")
    public List<FlightBooking> getFlightBookings(){
        return travelerService.getAllFlightBookings();
    }

    @GetMapping("/flight_bookings/flight/{flightId}")
    public FlightBooking getFlightBookingByFlight(@PathVariable String flightId){
        return travelerService.getFlightBookingByFlight(flightId);
    }

    @GetMapping("/flight_bookings/booking/{bookingId}")
    public FlightBooking getFlightBookingByBooking(@PathVariable String bookingId){
        return travelerService.getFlightBookingByBooking(bookingId);
    }

    @GetMapping("/booking_payments")
    public List<BookingPayment> getAllBookingPayments(){
        return travelerService.getAllBookingPayments();
    }

    @GetMapping("/booking_payments/{stripeId}")
    public BookingPayment getBookingPaymentByStripeId(@PathVariable String stripeId){
        return travelerService.getBookingPaymentsByStripeId(stripeId);
    }
}
