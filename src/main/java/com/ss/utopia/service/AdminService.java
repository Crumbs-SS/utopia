package com.ss.utopia.service;


import com.ss.utopia.dao.*;
import com.ss.utopia.dto.BookingDTO;
import com.ss.utopia.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = { Exception.class })
public class AdminService {
    @Autowired
    FlightDAO fdao;
    @Autowired
    RouteDAO rdao;
    @Autowired
    AirplaneDAO airplaneDAO;
    @Autowired
    AirportDAO airportDAO;
    @Autowired
    UserDAO udao;
    @Autowired
    SeatDAO seatDAO;
    @Autowired
    BookingDAO bdao;
    @Autowired
    BookingUserDAO bookingUserDAO;
    @Autowired
    BookingAgentDAO bookingAgentDAO;
    @Autowired
    BookingGuestDAO bookingGuestDAO;
    @Autowired
    BookingPaymentDAO bookingPaymentDAO;
    @Autowired
    FlightBookingDAO flightBookingDAO;
    @Autowired
    PassengerDAO passengerDAO;

    public List<Flight> getFlights() {
        List<Flight> flights = null;
        try {
            flights = fdao.getAllFlights();

            for (Flight flight : flights) {
                flight.setRoute(rdao.getRouteById(flight.getRoute().getId()));
                flight.setAirplane(airplaneDAO.getAirplaneById(flight.getAirplane().getId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flights;
    }

    // TODO
    public String addFlight(Flight f) {
        try {
            int result = fdao.addFlight(f);
            if (result == 1) {
                return "flight added";
            }
            return "flight not added";
        } catch (Exception e) {
            e.printStackTrace();
            return "flight not added";
        }
    }

    public String updateFlight(Flight f)  {
        try {
            Flight existingFlight = fdao.getFlightFromId(f.getId());
            if(f.getRoute() == null) { f.setRoute(existingFlight.getRoute()); }
            if(f.getAirplane() == null) { f.setAirplane(existingFlight.getAirplane()); }
            if(f.getDepartTime() == null) { f.setDepartTime(existingFlight.getDepartTime()); }
            if(f.getReservedSeats() == null) { f.setReservedSeats(existingFlight.getReservedSeats()); }
            if(f.getSeatPrice() == null) { f.setSeatPrice(existingFlight.getSeatPrice()); }
            int result = fdao.updateFlight(f);
            if (result == 1) {
                return "updated flight with id=" + f.getId();
            }
            return "could not updated flight with id=" + f.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return "could not updated flight with id=" + f.getId();
        }
    }

    public String deleteFlight(int flightId) {
        try {
            int result = fdao.deleteFlight(new Flight(flightId));
            if (result == 1) {
                return "Flight with id=" + flightId + " deleted.";
            }
            return "Flight could not be deleted";
        } catch (Exception e) {
            e.printStackTrace();
            return "Flight could not be deleted";
        }
    }

    public List<Airport> getAirports() {
        List<Airport> airports = null;
        try {
            airports = airportDAO.getAllAirports();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airports;
    }

    public String addAirport(Airport a) {
        try {
            int result = airportDAO.addAirport(a);
            if (result == 1) {
                return "Airport with code=" + a.getAirportCode() + " added.";
            }
            return "Airport could not be added.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Airport could not be added.";
        }
    }

    public String updateAirport(Airport a) {
        try {
            if (a.getCityName() == null) {
                Airport existingAirport = airportDAO.getAirportByAirportCode(a.getAirportCode());
                a.setCityName(existingAirport.getCityName());
            }
            int result = airportDAO.updateAirport(a);
            if (result == 1) {
                return "Airport with code=" + a.getAirportCode() + " updated.";
            }
            return "Airport could not be updated.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Airport could not be updated.";
        }
    }

    public String deleteAirport(String airportId) {
        try {
            int result = airportDAO.deleteAirport(new Airport(airportId, "ph"));
            if (result == 1) {
                return "Airport with id=" + airportId + " deleted.";
            }
            return "Airport could not be deleted";
        } catch (Exception e) {
            e.printStackTrace();
            return "Airport could not be deleted";
        }
    }

    public List<User> getTravelers() {
        List<User> users = null;
        try {
            users = udao.getAllTravelers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public String addTraveler(User t) {
        try {
            int result = udao.addUser(t);
            if (result == 1) {
                return "Traveler added.";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Traveler could not be added.";
    }

    public String updateTraveler(User t) {
        try {
            User existingTraveler = udao.getUserById(t.getId());
            if (t.getGivenName() == null) { t.setGivenName(existingTraveler.getGivenName()); }
            if (t.getFamilyName() == null) { t.setFamilyName(existingTraveler.getFamilyName()); }
            if (t.getUsername() == null) { t.setUsername(existingTraveler.getUsername()); }
            if (t.getEmail() == null) { t.setEmail(existingTraveler.getEmail()); }
            if (t.getPassword() == null) { t.setPassword(existingTraveler.getPassword()); }
            if (t.getPhone() == null) { t.setPhone(existingTraveler.getPhone()); }
            int result = udao.updateUser(t);
            if (result == 1) {
                return "Traveler updated.";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Traveler could not be updated.";
    }

    public String deleteTraveler(int id) {
        try {
            int result = udao.deleteUser(new User(id));
            if (result == 1) {
                return "deleted Traveler with id=" + id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Could not delete Traveler with id=" + id;
    }


    public List<User> getEmployees() {
        List<User> users = null;
        try {
            users = udao.getAllEmployees();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public String addEmployee(User e) {
        try {
            int result = udao.addUser(e);
            if (result == 1) {
                return "Employee added.";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Employee could not be added.";
    }

    public String updateEmployee(User e) {
        try {
            User existingEmployee = udao.getUserById(e.getId());
            if (e.getGivenName() == null) { e.setGivenName(existingEmployee.getGivenName()); }
            if (e.getFamilyName() == null) { e.setFamilyName(existingEmployee.getFamilyName()); }
            if (e.getUsername() == null) { e.setUsername(existingEmployee.getUsername()); }
            if (e.getEmail() == null) { e.setEmail(existingEmployee.getEmail()); }
            if (e.getPassword() == null) { e.setPassword(existingEmployee.getPassword()); }
            if (e.getPhone() == null) { e.setPhone(existingEmployee.getPhone()); }
            int result = udao.updateUser(e);
            if (result == 1) {
                return "Employee updated.";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Employee could not be updated.";
    }

    public String deleteEmployee(int id) {
        try {
            int result = udao.deleteUser(new User(id));
            if (result == 1) {
                return "deleted Employee with id=" + id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Could not delete Employee with id=" + id;
    }

    public List<Seat> getSeats() {
        List<Seat> seats = null;
        try {
            seats = seatDAO.getAllSeats();

            for (Seat seat : seats) {
                Flight temp = fdao.getFlightFromId(seat.getFlight().getId());
                temp.setRoute(rdao.getRouteById(seat.getFlight().getId()));
                temp.setAirplane(airplaneDAO.getAirplaneById(seat.getFlight().getId()));
                seat.setFlight(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return seats;
    }

    public void addSeats(Seat seat) {
        try {
            seatDAO.addSeat(seat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSeats(Seat seat) {
        try{
            Seat temp = seatDAO.getSeatFromId(seat.getFlight().getId());
            if(temp == null) throw new Exception("id was not provided");

            if(seat.getFirst() == null)
                seat.setFirst(temp.getFirst());

            if(seat.getBusiness() == null)
                seat.setBusiness(temp.getBusiness());

            if(seat.getEconomy() == null)
                seat.setEconomy(temp.getEconomy());

            seatDAO.updateSeat(seat);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteSeats(int id) {
        try {
            seatDAO.deleteSeat(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Booking> getBookings() {
        List<Booking> bookings = null;
        try {
            bookings = bdao.getAllBookings();

            /*
            for (Booking b : bookings) {

            }
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public String addBooking(BookingDTO bdto){
        try{
            if (null == bdto.getUserId() && null == bdto.getBookingGuest()) {
                return "Could not add booking. No booking agent/user or guest specified.";
            }
            int amountOfBookings = bdao.getAllBookings().size();
            Booking booking = new Booking(true, "CONFIRMATION-" + amountOfBookings);
            Flight flight = fdao.getFlightFromId(bdto.getFlightId());
            String stripeId = bdto.getStripeId();

            bdao.addBooking(booking);
            booking = bdao.getBookingByCode(booking.getConfirmationCode());

            bookingPaymentDAO.addBookingPayment(new BookingPayment(stripeId, false, booking));
            flightBookingDAO.addFlightBooking(new FlightBooking(flight, booking));

            if (null != bdto.getUserId()) {
                User user = udao.getUserById(bdto.getUserId());
                UserRole role = user.getUserRole();
                if (role.getName().equals("ADMIN")) { throw new IllegalArgumentException(); }
                else if (role.getName().equals("AGENT")) {
                    bookingAgentDAO.addBookingAgent(new BookingAgent(booking, user));
                }
                else if (role.getName().equals("CUSTOMER")) {
                    bookingUserDAO.addBookingUser(new BookingUser(user, booking));
                }
            }
            else {
                bdto.getBookingGuest().setBooking(booking);
                bookingGuestDAO.addBookingGuest(bdto.getBookingGuest());
            }

            return "Booking added.";
        }catch(Exception e){
            e.printStackTrace();
            return "Booking could not be added.";
        }
    }
/*
    public String updateBooking(BookingDTO bdto) {
        // let them change associated flight
        // let them change stripe_id
        // let them change confirmation code
        // let them change booking agent/user/guest
        try {
            Booking existingBooking = bdao.getBookingById(bdto.getId());
            if (null == existingBooking) {
                return "Could not update booking.";
            }
            Booking newBooking = new Booking();
            newBooking.setId(existingBooking.getId());
            newBooking.setIsActive(existingBooking.getIsActive());

            BookingPayment existingBookingPayment =
                    bookingPaymentDAO.getBookingPaymentByBooking(existingBooking);
            FlightBooking existingFlightBooking =
                    flightBookingDAO.getFlightBookingByBooking(existingBooking);



            User user = udao.getUserById(bdto.getUserId());
            Flight flight = fdao.getFlightFromId(bdto.getFlightId());
            String stripeId = bdto.getStripeId();

            bdao.addBooking(booking);
            booking = bdao.getBookingByCode(booking.getConfirmationCode());

            bookingUserDAO.addBookingUser(new BookingUser(user, booking));
            bookingPaymentDAO.addBookingPayment(new BookingPayment(stripeId, false, booking));
            flightBookingDAO.addFlightBooking(new FlightBooking(flight, booking));
            return "Booking added.";
        }catch(Exception e){
            e.printStackTrace();
            return "Booking could not be added.";
        }
    }
*/
}
