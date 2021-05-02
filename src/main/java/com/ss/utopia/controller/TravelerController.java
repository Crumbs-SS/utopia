package com.ss.utopia.controller;


import com.ss.utopia.dto.BookingDTO;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.User;
import com.ss.utopia.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utopia/traveler")
public class TravelerController {
    @Autowired TravelerService travelerService;

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getFlights() {
        List<Flight> flights = travelerService.getFlights();

        return flights != null ? new ResponseEntity<>(flights, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){
        User user = travelerService.getUser(id);

        return user != null ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getBookings(){
        List<Booking> bookingList =  travelerService.getAllBookings();

        return bookingList != null ? new ResponseEntity<>(bookingList, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable String id){
        return travelerService.cancelBooking(id) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/bookings")
    public ResponseEntity<Booking> addBooking(@RequestBody BookingDTO bookingDTO){
        Booking booking = travelerService.addBooking(bookingDTO);

        return booking != null ? new ResponseEntity<>(booking, HttpStatus.CREATED)
                : new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user){
        User userFound = travelerService.login(user);

        return userFound != null ? new ResponseEntity<>(userFound, HttpStatus.ACCEPTED)
                : new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
    }

}
