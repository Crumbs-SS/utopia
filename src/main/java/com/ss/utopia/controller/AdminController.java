package com.ss.utopia.controller;

import com.ss.utopia.entity.*;
import com.ss.utopia.service.AdminService;
import com.ss.utopia.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/utopia/admin")
public class AdminController extends BaseController {

    @Autowired
    AdminService as;

    @GetMapping("/flights")
    public List<Flight> getFlights() {
        return as.getFlights();
    }

    // TODO
    @PostMapping("/flights")
    public String addFlight(@RequestBody Flight f) {
        return as.addFlight(f);
    }

    // TODO
    @PutMapping("/flights/{flightId}")
    public String updateFlight(@PathVariable int flightId, @RequestBody Flight f) {
        f.setId(flightId);
        return as.updateFlight(f);
    }

    @DeleteMapping("/flights/{flightId}")
    public String deleteFlight(@PathVariable int flightId) {
        return as.deleteFlight(flightId);
    }

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

    // TODO - JDBCTemplate.update returns an int, 0 = no modifications.
    @DeleteMapping("/airports/{airportCode}")
    public String deleteAirport(@PathVariable String airportCode) {
        return as.deleteAirport(airportCode);
    }


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

    //Seats
    @GetMapping("/seats")
    public List<Seat> getSeats(){return as.getSeats();}

    @PostMapping("/addSeats")
    public void addSeats(@RequestBody Seat seats){ as.addSeats(seats); }

    @PutMapping("/updateSeats/{id}")
    public void updateSeats(@RequestBody Seat seat) { as.updateSeats(seat);}

    @DeleteMapping("/deleteSeats/{id}")
    public void deleteSeats(@PathVariable int id){ as.deleteSeats(id); }

}
