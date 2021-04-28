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

    @GetMapping("/flights/{id}")
    public Flight getFlight(@PathVariable String id){
        return employeeService.getFlight(id);
    }

    @PutMapping("/updateFlightRoute/{id}")
    public void updateFlightRoute(@PathVariable String id, @RequestParam Integer routeID){
       employeeService.updateFlightRoute(id, routeID);
    }

    @PutMapping("/updateFlightAirplane/{id}")
    public void updateFlightAirplane(@PathVariable String id, @RequestParam Integer airplaneID){
        employeeService.updateFlightAirplane(id, airplaneID);
    }

    @PutMapping("/updateFlightDepartTime/{id}")
    public void updateFlightDepartTime(@PathVariable String id, @RequestParam Timestamp newTime){
        employeeService.updateFlightDepartTime(id, newTime);
    }

    @PutMapping("/updateFlightSeats/{id}")
    public void updateFlightSeats(@PathVariable String id, @RequestParam Integer reservedSeats){
        employeeService.updateFlightSeats(id, reservedSeats);
    }

    @PutMapping("/updateFlightSeatPrice/{id}")
    public void updateFlightSeatPrice(@PathVariable String id, @RequestParam Float seatPrice){
        employeeService.updateFlightSeatPrice(id, seatPrice);
    }

    @PutMapping("/updateFlight/{id}")
    public void updateFlight(@PathVariable String id, @RequestParam Integer routeID, @RequestParam Integer airplaneID,
                               @RequestParam Timestamp deparTime, @RequestParam Integer reservedSeats,
                               @RequestParam Float seatPrice)
    {
        employeeService.updateFlight(id, routeID, airplaneID,deparTime, reservedSeats, seatPrice);
    }


}
