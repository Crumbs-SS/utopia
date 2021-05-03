
package com.ss.utopia.controller;

import com.ss.utopia.dto.BookingDTO;
import com.ss.utopia.entity.*;
import com.ss.utopia.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utopia/admin")
public class AdminController extends BaseController {

    @Autowired
    AdminService as;

    // Flights ------------------------------------------------
    @GetMapping("/flights")
    public List<Flight> getFlights() {
        return as.getFlights();
    }

    @PostMapping("/flights")
    public String addFlight(@RequestBody Flight f) {
        return as.addFlight(f);
    }

    @PutMapping("/flights/{flightId}")
    public String updateFlight(@PathVariable int flightId, @RequestBody Flight f) {
        f.setId(flightId);
        return as.updateFlight(f);
    }

    @DeleteMapping("/flights/{flightId}")
    public String deleteFlight(@PathVariable int flightId) {
        return as.deleteFlight(flightId);
    }

    //Seats ----------------------------------------------------------------
    @GetMapping("/seats")
    public List<Seat> getSeats(){return as.getSeats();}

    @PostMapping("/seats")
    public void addSeats(@RequestBody Seat seats){ as.addSeats(seats); }

    @PutMapping("/seats/{id}")
    public void updateSeats(@RequestBody Seat seat) { as.updateSeats(seat);}

    @DeleteMapping("/seats/{id}")
    public void deleteSeats(@PathVariable int id){ as.deleteSeats(id); }


    // Bookings ----------------------------------------------------
    @GetMapping("/bookings")
    public List<Booking> getBookings() {
        return as.getBookings();
    }

    @PostMapping("/bookings")
    public String addBooking(@RequestBody BookingDTO bdto) {
        return as.addBooking(bdto);
    }

    @PutMapping("/bookings/{id}")
    public String updateBooking(@PathVariable int id, @RequestBody BookingDTO bdto) {
        bdto.setId(id);
        return as.updateBooking(bdto);
    }

    @DeleteMapping("/bookings/{id}")
    public String deleteBooking(@PathVariable int id) {
        return as.deleteBooking(id);
    }

    @PutMapping("/bookingsCancel/{id}")
    public String cancelBooking(@PathVariable int id) {
        return as.cancelBooking(id);
    }

    @PutMapping("/bookingsUncancel/{id}")
    public String uncancelBooking(@PathVariable int id) {
        return as.uncancelBooking(id);
    }

    // Passengers --------------------------------------------------

    @GetMapping("/passengers")
    public List<Passenger> getPassengers() {
        return as.getPassengers();
    }

    @GetMapping("/passengers/{id}")
    public Passenger getPassengerById(@PathVariable int id) {
        return as.getPassengerById(id);
    }

    @GetMapping("/passengers/bookings/{bookingId}")
    public List<Passenger> getPassengersByBookingId(@PathVariable int bookingId) {
        return as.getPassengersByBookingId(bookingId);
    }

    @PostMapping("/passengers/bookings/{bookingId}")
    public String addPassenger(@PathVariable int bookingId, @RequestBody Passenger p) {
        p.getBooking().setId(bookingId);
        return as.addPassenger(p);
    }

    @PutMapping("/passengers/{id}")
    public String updatePassenger(@PathVariable int id, @RequestBody Passenger p) {
        p.setId(id);
        return as.updatePassenger(p);
    }

    @DeleteMapping("/passengers/{id}")
    public String deletePassenger(@PathVariable int id) {
        return as.deletePassenger(id);
    }

    // Airports ----------------------------------------------------
    @GetMapping("/airports")
    public List<Airport> getAirports() {
        return as.getAirports();
    }

    @PostMapping("/airports")
    public String addAirport(@RequestBody Airport a) {
        return as.addAirport(a);
    }

    @PutMapping("/airports/{airportCode}")
    public String updateAirport(@PathVariable String airportCode, @RequestBody Airport a) {
        a.setAirportCode(airportCode);
        return as.updateAirport(a);
    }

    @DeleteMapping("/airports/{airportCode}")
    public String deleteAirport(@PathVariable String airportCode) {
        return as.deleteAirport(airportCode);
    }

    // Travelers -------------------------------------------------------
    @GetMapping("/travelers")
    public List<User> getTravelers() {
        return as.getTravelers();
    }

    final int TRAVELER = 2;
    @PostMapping("/travelers")
    public String addTraveler(@RequestBody User t) {
        t.setUserRole(new UserRole(TRAVELER, "ph"));
        return as.addTraveler(t);
    }

    @PutMapping("/travelers/{id}")
    public String updateTraveler(@PathVariable int id, @RequestBody User t) {
        t.setId(id);
        t.setUserRole(new UserRole(TRAVELER, "ph"));
        return as.updateTraveler(t);
    }

    @DeleteMapping("/travelers/{id}")
    public String deleteTraveler(@PathVariable int id) {
        return as.deleteTraveler(id);
    }

    // Employees --------------------------------------------
    @GetMapping("/employees")
    public List<User> getEmployees() {
        return as.getEmployees();
    }

    final int EMPLOYEE = 3;
    @PostMapping("/employees")
    public String addEmployee(@RequestBody User e) {
        e.setUserRole(new UserRole(EMPLOYEE, "ph"));
        return as.addEmployee(e);
    }

    @PutMapping("/employees/{id}")
    public String updateEmployee(@PathVariable int id, @RequestBody User e) {
        e.setId(id);
        e.setUserRole(new UserRole(EMPLOYEE, "ph"));
        return as.updateEmployee(e);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        return as.deleteEmployee(id);
    }



}
