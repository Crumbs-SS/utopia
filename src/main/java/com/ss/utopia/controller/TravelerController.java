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
public class TravelerController extends BaseController {

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
        return travelerService.addBooking(bookingDTO);
    }

    @PutMapping("/bookings/{id}")
    public void cancelBooking(@PathVariable String id){
        travelerService.cancelBooking(id);
    }
}
