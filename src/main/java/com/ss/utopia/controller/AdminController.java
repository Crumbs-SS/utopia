package com.ss.utopia.controller;

import com.ss.utopia.dto.BookingDTO;
import com.ss.utopia.entity.*;
import com.ss.utopia.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utopia/admin")
public class AdminController {

    @Autowired AdminService as;

    // Flights ------------------------------------------------
    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getFlights() {
        List<Flight> flights = as.getFlights();
        return flights != null ? new ResponseEntity<>(flights, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @PostMapping("/flights")
    public ResponseEntity<String> addFlight(@Validated @RequestBody Flight f) {
        Flight flight = as.addFlight(f);
        return flight != null ? new ResponseEntity<>("Flight added.", HttpStatus.OK)
                : new ResponseEntity<>("Flight could not be added.", HttpStatus.CONFLICT);
    }

    @PutMapping("/flights/{flightId}")
    public ResponseEntity<String> updateFlight(@PathVariable int flightId, @Validated @RequestBody Flight f) {
        Flight flight = as.updateFlight(flightId, f);
        return flight != null ? new ResponseEntity<>("Flight updated.", HttpStatus.OK)
                : new ResponseEntity<>("Flight could not be updated.", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/flights/{flightId}")
    public ResponseEntity<String> deleteFlight(@PathVariable int flightId) {
        String msg = as.deleteFlight(flightId);
        return msg != null ? new ResponseEntity<>(msg, HttpStatus.OK)
                : new ResponseEntity<>("Flight could not be deleted.", HttpStatus.CONFLICT);
    }

    //Seats ----------------------------------------------------------------
    @GetMapping("/seats")
    public ResponseEntity<List<Seats>> getSeats() {
        List<Seats> seats = as.getSeats();
        return seats != null ? new ResponseEntity<>(seats, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }
/*
    @PostMapping("/seats/{flightId}")
    public ResponseEntity<String> addSeats(@PathVariable int flightId, @Validated @RequestBody Seats seats) {
        Seats s = as.addSeats(flightId, seats);
        return s != null ? new ResponseEntity<>("Seats added.", HttpStatus.OK)
                : new ResponseEntity<>("Seats could not be added.", HttpStatus.CONFLICT);
    }
*/
    @PutMapping("/seats/{id}")
    public ResponseEntity<String> updateSeats(@PathVariable int id, @Validated @RequestBody Seats seat) {
        Seats s = as.updateSeats(id, seat);
        return s != null ? new ResponseEntity<>("Seats updated.", HttpStatus.OK)
                : new ResponseEntity<>("Seats could not be updated.", HttpStatus.CONFLICT);
    }
/*
    @DeleteMapping("/seats/{id}")
    public ResponseEntity<String> deleteSeats(@PathVariable int id) {
        String msg = as.deleteSeats(id);
        return msg != null ? new ResponseEntity<>(msg, HttpStatus.OK)
                : new ResponseEntity<>("Seats could not be deleted.", HttpStatus.CONFLICT);
    }
*/

    // Bookings ----------------------------------------------------
    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getBookings() {
        List<Booking> b = as.getBookings();
        return b != null ? new ResponseEntity<>(b, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @PostMapping("/bookings")

    public ResponseEntity<String> addBooking(@RequestBody BookingDTO bdto) {
        Booking b = as.addBooking(bdto);
        return b != null ? new ResponseEntity<>("Booking added.", HttpStatus.OK)
                : new ResponseEntity<>("Booking could not be added.", HttpStatus.CONFLICT);
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable int id, @Validated @RequestBody BookingDTO bdto) {
        Booking b = as.updateBooking(id, bdto);
        return b != null ? new ResponseEntity<>("Booking updated.", HttpStatus.OK)
                : new ResponseEntity<>("Booking could not be updated.", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable int id) {
        String msg = as.deleteBooking(id);
        return msg != null ? new ResponseEntity<>(msg, HttpStatus.OK)
                : new ResponseEntity<>("Booking could not be deleted.", HttpStatus.CONFLICT);
    }

    @PutMapping("/bookingsCancel/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable int id) {
        Booking b = as.cancelBooking(id);
        return b != null ? new ResponseEntity<>("Booking canceled", HttpStatus.OK)
                : new ResponseEntity<>("Booking could not be canceled.", HttpStatus.CONFLICT);
    }

    @PutMapping("/bookingsUncancel/{id}")
    public ResponseEntity<String> uncancelBooking(@PathVariable int id) {
        Booking b = as.uncancelBooking(id);
        return b != null ? new ResponseEntity<>("Booking reactivated", HttpStatus.OK)
                : new ResponseEntity<>("Booking could not be reactivated.", HttpStatus.CONFLICT);
    }

    // Passengers --------------------------------------------------

    @GetMapping("/passengers")
    public ResponseEntity<List<Passenger>> getPassengers() {
        List<Passenger> p = as.getPassengers();
        return p != null ? new ResponseEntity<>(p, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @GetMapping("/passengers/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable int id) {
        Passenger p = as.getPassengerById(id);
        return p != null ? new ResponseEntity<>(p, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @GetMapping("/passengers/bookings/{bookingId}")
    public ResponseEntity<List<Passenger>> getPassengersByBookingId(@PathVariable int bookingId) {
        List<Passenger> p = as.getPassengersByBookingId(bookingId);
        return p != null ? new ResponseEntity<>(p, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @PostMapping("/passengers/bookings/{bookingId}")
    public ResponseEntity<String> addPassenger(@PathVariable int bookingId, @Validated @RequestBody Passenger p) {
        Passenger passenger = as.addPassenger(bookingId, p);
        return passenger != null ? new ResponseEntity<>("Passenger added.", HttpStatus.OK)
                : new ResponseEntity<>("Passenger could not be added.", HttpStatus.CONFLICT);
    }

    @PutMapping("/passengers/{id}")
    public ResponseEntity<String> updatePassenger(@PathVariable int id, @Validated @RequestBody Passenger p) {
        Passenger passenger = as.updatePassenger(id, p);
        return passenger != null ? new ResponseEntity<>("Passenger updated.", HttpStatus.OK)
                : new ResponseEntity<>("Passenger could not be updated.", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/passengers/{id}")
    public ResponseEntity<String> deletePassenger(@PathVariable int id) {
        String msg = as.deletePassenger(id);
        return msg != null ? new ResponseEntity<>(msg, HttpStatus.OK)
                : new ResponseEntity<>("Passenger could not be deleted.", HttpStatus.CONFLICT);
    }

    // Airports ----------------------------------------------------
    @GetMapping("/airports")
    public ResponseEntity<List<Airport>> getAirports() {
        List<Airport> a = as.getAirports();
        return a != null ? new ResponseEntity<>(a, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @PostMapping("/airports")
    public ResponseEntity<String> addAirport(@Validated @RequestBody Airport a) {
        Airport airport = as.addAirport(a);
        return airport != null ? new ResponseEntity<>("Airport added.", HttpStatus.OK)
                : new ResponseEntity<>("Airport could not be added.", HttpStatus.CONFLICT);
    }

    @PutMapping("/airports/{airportCode}")
    public ResponseEntity<String> updateAirport(@PathVariable String airportCode, @Validated @RequestBody Airport a) {
        Airport airport = as.updateAirport(airportCode, a);
        return airport != null ? new ResponseEntity<>("Airport updated.", HttpStatus.OK)
                : new ResponseEntity<>("Airport could not be updated.", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/airports/{airportCode}")
    public ResponseEntity<String> deleteAirport(@PathVariable String airportCode) {
        String msg = as.deleteAirport(airportCode);
        return msg != null ? new ResponseEntity<>(msg, HttpStatus.OK)
                : new ResponseEntity<>("Airport could not be deleted.", HttpStatus.CONFLICT);
    }

    // Travelers -------------------------------------------------------
    @GetMapping("/travelers")
    public ResponseEntity<List<User>> getTravelers() {
        List<User> t = as.getTravelers();
        return t != null ? new ResponseEntity<>(t, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }


    @PostMapping("/travelers")
    public ResponseEntity<String> addTraveler(@Validated @RequestBody User t) {
        User traveler = as.addTraveler(t);
        return traveler != null ? new ResponseEntity<>("Traveler added.", HttpStatus.OK)
                : new ResponseEntity<>("Traveler could not be added.", HttpStatus.CONFLICT);
    }

    @PutMapping("/travelers/{id}")
    public ResponseEntity<String> updateTraveler(@PathVariable int id, @Validated @RequestBody User t) {
        User traveler = as.updateTraveler(id, t);
        return traveler != null ? new ResponseEntity<>("Traveler updated.", HttpStatus.OK)
                : new ResponseEntity<>("Traveler could not be updated.", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/travelers/{id}")
    public ResponseEntity<String> deleteTraveler(@PathVariable int id) {
        String msg = as.deleteTraveler(id);
        return msg != null ? new ResponseEntity<>(msg, HttpStatus.OK)
                : new ResponseEntity<>("Traveler could not be deleted.", HttpStatus.CONFLICT);
    }

    // Employees --------------------------------------------
    @GetMapping("/employees")
    public ResponseEntity<List<User>> getEmployees() {
        List<User> employee = as.getEmployees();
        return employee != null ? new ResponseEntity<>(employee, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }


    @PostMapping("/employees")
    public ResponseEntity<String> addEmployee(@Validated @RequestBody User e) {
        User employee = as.addEmployee(e);
        return employee != null ? new ResponseEntity<>("Employee added.", HttpStatus.OK)
                : new ResponseEntity<>("Employee could not be added.", HttpStatus.CONFLICT);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable int id, @Validated @RequestBody User e) {
        User employee = as.updateEmployee(id, e);
        return employee != null ? new ResponseEntity<>("Employee updated.", HttpStatus.OK)
                : new ResponseEntity<>("Employee could not be updated.", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        String msg = as.deleteEmployee(id);
        return msg != null ? new ResponseEntity<>(msg, HttpStatus.OK)
                : new ResponseEntity<>("Employee could not be deleted.", HttpStatus.CONFLICT);
    }
}