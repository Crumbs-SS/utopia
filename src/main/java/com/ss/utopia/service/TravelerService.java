package com.ss.utopia.service;

import com.ss.utopia.entity.*;
import com.ss.utopia.dto.BookingDTO;
import com.ss.utopia.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = { Exception.class })
public class TravelerService {

    @Autowired FlightRepository flightRepository;
    @Autowired AirplaneRepository airplaneRepository;
    @Autowired RouteRepository routeRepository;
    @Autowired UserRepository userRepository;
    @Autowired BookingRepository bookingRepository;
    @Autowired BookingUserRepository bookingUserRepository;
    @Autowired BookingPaymentRepository bookingPaymentRepository;
    @Autowired FlightBookingRepository flightBookingRepository;
    @Autowired PassengerRepository passengerRepository;


    public List<Flight> getFlights() {
        List<Flight> flights = null;
        try {
            flights = flightRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flights;
    }

    public User getUser(String id){
        Integer userId = Integer.parseInt(id);
        User user = null;

        try{
            user = userRepository.findById(userId).orElseThrow();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public User login(User body){
        User user = null;
        try{
            user = userRepository.authenticateUser(body.getUsername(), body.getPassword());
        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public Booking addBooking(BookingDTO bookingDTO){
        Booking booking = null;
        try{
            String confirmationCode = "CONFIRMATION-" + (bookingRepository.findAll().size() + 1);

            booking = bookingRepository.saveAndFlush(new Booking(true, confirmationCode));
            Flight flight = flightRepository.findById(bookingDTO.getFlightId()).orElseThrow();
            User user = userRepository.findById(bookingDTO.getUserId()).orElseThrow();

            flightBookingRepository.save(new FlightBooking(flight, booking));
            bookingPaymentRepository.save(new BookingPayment(bookingDTO.getStripeId(), false));
            bookingUserRepository.save(new BookingUser(user, booking));

            setPassengers(booking, bookingDTO.getPassengers());
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void cancelBooking(String bookingId){
        try{
            Integer id = Integer.parseInt(bookingId);
            Booking booking = bookingRepository.findById(id).orElseThrow();
            if(booking != null){
                BookingPayment bookingPayment = bookingPaymentRepository.getBookingPaymentByBooking(booking);

                booking.setIsActive(false);
                bookingPayment.setRefunded(true);

                bookingRepository.save(booking);
                bookingPaymentRepository.save(bookingPayment);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setPassengers(Booking booking, List<Passenger> passengers){
        for (Passenger passenger : passengers) {
            try{
                passenger.setBooking(booking);
                passengerRepository.save(passenger);
            }catch(Exception e){
            }

        }
    }
}
