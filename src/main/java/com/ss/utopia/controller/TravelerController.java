package com.ss.utopia.controller;


import com.ss.utopia.dto.BookingDTO;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.User;
import com.ss.utopia.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/traveler")
public class TravelerController {
    @Autowired
    TravelerService travelerService;

    @GetMapping("/flights")
    public List<Flight> getFlights() {
        return travelerService.getFlights();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable String id){
        return travelerService.getUser(id);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user){
        return travelerService.login(user) != null ? "Success" : "Fail";
    }

    @PostMapping("/bookings")
    public Booking addBooking(@RequestBody BookingDTO bookingDTO){
        System.out.println(bookingDTO.getFlightId());
        System.out.println(bookingDTO.getUserId());
        System.out.println(bookingDTO.getStripeId());

        return travelerService.addBooking(bookingDTO);
    }

    @GetMapping("/bookings")
    public List<Booking> getBookings(){
        return travelerService.getAllBookings();
    }

    @PutMapping("/bookings/{id}")
    public void cancelBooking(@PathVariable String id){
        travelerService.cancelBooking(id);
    }
}
