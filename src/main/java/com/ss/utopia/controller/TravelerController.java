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

}
