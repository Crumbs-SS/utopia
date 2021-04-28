package com.ss.utopia.controller;

import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.User;
import com.ss.utopia.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/utopia/employee")
public class EmployeeController extends BaseController{

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/flights/{id}")
    public Flight getFlight(@PathVariable String id){
        return employeeService.getFlight(id);
    }

    @PutMapping("/updateFlightRoute/{id}")
    public Flight updateFlightRoute(@PathVariable String id, @RequestParam Integer routeID){
        return employeeService.updateFlightRoute(id, routeID);
    }

    @PutMapping("/updateFlightAirplane/{id}")
    public Flight updateFlightAirplane(@PathVariable String id, @RequestParam Integer airplaneID){
        return employeeService.updateFlightAirplane(id, airplaneID);
    }

    @PutMapping("/updateFlightDepartTime/{id}")
    public Flight updateFlightDepartTime(@PathVariable String id, @RequestParam Timestamp newTime){
        return employeeService.updateFlightDepartTime(id, newTime);
    }

    @PutMapping("/updateFlightSeats/{id}")
    public Flight updateFlightSeats(@PathVariable String id, @RequestParam Integer reservedSeats){
        return employeeService.updateFlightSeats(id, reservedSeats);
    }

    @PutMapping("/updateFlightSeatPrice/{id}")
    public Flight updateFlightSeatPrice(@PathVariable String id, @RequestParam Float seatPrice){
        return employeeService.updateFlightSeatPrice(id, seatPrice);
    }

    @PutMapping("/updateFlight/{id}")
    public Flight updateFlight(@PathVariable String id, @RequestParam Integer routeID, @RequestParam Integer airplaneID,
                               @RequestParam Timestamp deparTime, @RequestParam Integer reservedSeats,
                               @RequestParam Float seatPrice)
    {
        return employeeService.updateFlight(id, routeID, airplaneID,deparTime, reservedSeats, seatPrice);
    }


}
