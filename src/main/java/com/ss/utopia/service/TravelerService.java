package com.ss.utopia.service;

import com.ss.utopia.entity.*;
import com.ss.utopia.dto.BookingDTO;
import com.ss.utopia.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Date;
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
        List<Flight> flights;
        try {
            flights = flightRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return flights;
    }

    public User getUser(String id){
        User user;
        try{
            Integer userId = Integer.parseInt(id);
            user = userRepository.findById(userId).orElseThrow();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public User login(User body){
        User user;
        try{
            user = userRepository.authenticateUser(body.getUsername(), body.getPassword());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return user;
    }

    public Booking addBooking(BookingDTO bookingDTO){
        Booking booking;
        try{
            String confirmationCode = "CONFIRMATION-" + (bookingRepository.findAll().size() + 1);
            booking = bookingRepository.saveAndFlush(new Booking(true, confirmationCode));

            Flight flight = flightRepository.findById(bookingDTO.getFlightId()).orElseThrow();
            User user = userRepository.findById(bookingDTO.getUserId()).orElseThrow();

            BookingPayment bookingPayment = new BookingPayment(booking, bookingDTO.getStripeId(), false);
            bookingPaymentRepository.save(bookingPayment);
            flightBookingRepository.save(new FlightBooking(flight, booking));
            bookingUserRepository.save(new BookingUser(booking, user));

            setPassengers(booking, bookingDTO.getPassengers());
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return null;
        }

        return booking;
    }

    public boolean cancelBooking(String bookingId){
        try{
            Integer id = Integer.parseInt(bookingId);
            Booking booking = bookingRepository.findById(id).orElseThrow();
            BookingPayment bookingPayment = bookingPaymentRepository.
                    getBookingPaymentByConfirmationCode(booking.getConfirmationCode());

            booking.setActive(false);
            bookingPayment.setRefunded(true);

            bookingRepository.save(booking);
            bookingPaymentRepository.save(bookingPayment);
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

//    Private
    private void setPassengers(Booking booking, List<Passenger> passengers){
        for (Passenger passenger : passengers) {
            try{
                passenger.setBooking(booking);
                passengerRepository.save(passenger);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }


}
