package com.ss.utopia.controller;

import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Flight;
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
        String result = as.addFlight(f);
        return "flight added";
    }

    // TODO
    @PutMapping("/flights")
    public String updateFlight(@RequestBody Flight f) {
        String result = as.addFlight(f);
        return "flight updated";
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

    @PutMapping("/airports")
    public String updateAirport(@RequestBody Airport a) {
        return as.updateAirport(a);
    }


    // TODO - JDBCTemplate.update returns an int, 0 = no modifications.
    @DeleteMapping("/airports/{airportCode}")
    public String deleteAirport(@PathVariable String airportCode) {
        return as.deleteAirport(airportCode);
    }
}
