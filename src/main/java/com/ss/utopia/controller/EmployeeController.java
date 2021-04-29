package com.ss.utopia.controller;

import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Seats;
import com.ss.utopia.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utopia/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/flights/{id}")
    public Flight getFlight(@PathVariable Integer id){
        return employeeService.getFlight(id);
    }

    @PutMapping("/updateFlight")
    public String updateFlight(@RequestBody Flight flight) { return employeeService.updateFlight(flight); }

    @GetMapping("/seats")
    public List<Seats> getSeats(){return employeeService.getSeats();}

    @PutMapping("/updateSeats")
    public String updateSeats(@RequestBody Seats seat) { return employeeService.updateSeats(seat);}

}