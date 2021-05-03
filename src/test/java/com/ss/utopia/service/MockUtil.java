package com.ss.utopia.service;

import com.ss.utopia.dto.BookingDTO;
import com.ss.utopia.entity.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockUtil {

    public static List<Flight> getMockFlights(){
       List<Flight> flights = new ArrayList<>();

       flights.add(new Flight("time", 3, 121.20F));
       flights.add(new Flight("time", 10, 124.20F));
       flights.add(new Flight("time", 60, 224.20F));

       return flights;
    }
    public static List<Airport> getMockAirports(){
        List<Airport> airports = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            airports.add(getAirport());
        }

        return airports;
    }

    public static List<User> getTravelers(){
        List<User> travelers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            travelers.add(getUser());
        }

        return travelers;
    }

    public static List<Passenger> getPassengers(){
        List<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            passengers.add(getPassenger());
        }

        return passengers;
    }

    public static Passenger getPassenger(){
        return new Passenger(
                getBooking(),
                "TEST",
                "GUY",
                Date.valueOf("2000-05-01"),
                "MALE",
                "TEST ADDRESS"
        );
    }

    public static Optional<Passenger> getPassengerOptional(){
        return Optional.of(getPassenger());
    }

    public static List<User> getEmployees(){
        List<User> employees = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            employees.add(getUser());
        }

        return employees;
    }

    public static List<Seats> getMockSeats(){
        List<Seats> seats = new ArrayList<>();
        seats.add(new Seats());
        return seats;
    }
    public static Seats getSeat(){ return new Seats(1); }

    public static Optional<Seats> getSeatOptional(){
        return Optional.of(getSeat());
    }

    public static User getUser(){
        return new User(
                "TEST",
                "GUY",
                "TEST-GUY",
                "TESTGUY@GMAIL",
                "PASSWORD1",
                "123456"
        );
    }
    public static Airport getAirport(){
        return new Airport("TES", "TEST");
    }

    public static Optional<Airport> getAirportOptional(){
        return Optional.of(getAirport());
    }


    public static List<Booking> getBookings(){
        List<Booking> bookings = new ArrayList<>();

        bookings.add(new Booking(true, "TEST-CONFIRMATION1"));
        bookings.add(new Booking(true, "TEST-CONFIRMATION2"));
        bookings.add(new Booking(true, "TEST-CONFIRMATION3"));

        return bookings;
    }

    public static User authenticateUser(String username, String password) {
        if(username == "TEST-GUY" && password == "PASSWORD1")
            return getUser();

        return null;
    }


    public static Booking getBooking(){
        String confirmationCode = "CONFIRMATION-" + (getBookings().size() + 1);
        return new Booking(true, confirmationCode);
    }

    public static BookingDTO getBookingDTO(){
        return new BookingDTO();
    }

    public static BookingPayment getBookingPayment(){
        return new BookingPayment(getBooking(), null, false);
    }

    public static Flight getFlight(){
        return new Flight("time", 20, 20.53F);
    }

    public static FlightBooking getFlightBooking(){
       FlightBooking flightBooking = new FlightBooking(getFlightBookingID());
       flightBooking.setBooking(getBooking());
       flightBooking.setFlight(getFlight());

       return flightBooking;
    }

    public static BookingUser getBookingUser(){
        return new BookingUser(getBooking(), getUser());
    }

    public static Optional<User> getUserOptional(){
        return Optional.of(getUser());
    }

    public static Optional<Booking> getBookingOptional(){
        return Optional.of(getBooking());
    }

    public static Optional<BookingPayment> getBookingPaymentOptional(){
        return Optional.of(getBookingPayment());
    }

    public static Optional<Flight> getFlightOptional(){
        return Optional.of(getFlight());
    }

    private static FlightBookingID getFlightBookingID(){
        return new FlightBookingID(getFlight().getId(), getBooking().getId());
    }

}
