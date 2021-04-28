package com.ss.utopia.controller;

import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Seat;
import com.ss.utopia.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utopia/employee")
public class EmployeeController extends BaseController{

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/flights/{id}")
    public Flight getFlight(@PathVariable String id){
        return employeeService.getFlight(id);
    }

    @PutMapping("/updateFlight/{id}")
    public void updateFlight(@RequestBody Flight flight) { employeeService.updateFlight(flight); }

    @PutMapping("/addSeats/{id}")
    public void addSeats(@RequestBody Seat seat) { employeeService.addSeats(seat);}



}
