package com.ss.utopia.controller;

import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Seats;
import com.ss.utopia.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utopia/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable Integer id){
        Flight flight =  employeeService.getFlight(id);
        return flight != null ? new ResponseEntity<>(flight, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @GetMapping("/seats")
    public ResponseEntity<List<Seats>> getSeats() {
        List<Seats> seats = employeeService.getSeats();
        return seats != null ? new ResponseEntity<>(seats, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @PutMapping("/seats/{id}")
    public ResponseEntity<String> updateSeats(@PathVariable int id, @Validated @RequestBody Seats seat) {
        Seats s = employeeService.updateSeats(id, seat);
        return s != null ? new ResponseEntity<>("Seats updated.", HttpStatus.OK)
                : new ResponseEntity<>("Seats could not be updated.", HttpStatus.CONFLICT);
    }
    @PutMapping("/flights/{flightId}")
    public ResponseEntity<String> updateFlight(@PathVariable int flightId, @Validated @RequestBody Flight f) {
        Flight flight = employeeService.updateFlight(flightId, f);
        return flight != null ? new ResponseEntity<>("Flight updated.", HttpStatus.OK)
                : new ResponseEntity<>("Flight could not be updated.", HttpStatus.CONFLICT);
    }

    /*
      @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable Integer id){ return employeeService.getFlight(id); }

    @GetMapping("/seats")
    public ResponseEntity<List<Seats>> getSeats(){ return employeeService.getSeats();}

    @PutMapping("/updateSeats")
    public ResponseEntity<String> updateSeats(@RequestBody Seats seat) { return employeeService.updateSeats(seat);}
    @PutMapping("/updateFlight")
    public ResponseEntity<String> updateFlight(@RequestBody Flight flight) { return employeeService.updateFlight(flight); }
*/
}