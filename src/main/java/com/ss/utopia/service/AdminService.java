package com.ss.utopia.service;

import com.ss.utopia.dto.BookingDTO;
import com.ss.utopia.entity.*;
import com.ss.utopia.repo.AirportRepository;
import com.ss.utopia.repo.FlightRepository;
import com.ss.utopia.repo.SeatRepository;
import com.ss.utopia.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(rollbackFor = { Exception.class })
public class AdminService {

    @Autowired AirportRepository airportRepository;
    @Autowired FlightRepository flightRepository;
    @Autowired SeatRepository seatRepository;
    @Autowired UserRepository userRepository;

    //Read Methods
    public List<Airport> getAirports(){ return airportRepository.findAll(); }
    public List<Flight> getFlights(){return flightRepository.findAll(); }
    public List<Seats> getSeats() { return seatRepository.findAll(); }
    public List<User> getTravelers(){return userRepository.getTravellers(); }
    public List<User> getEmployees(){return userRepository.getEmployees(); }

    //Add Methods
    public Airport addAirport(Airport airport){return airportRepository.save(airport);}
    public Flight addFlight(Flight flight){return flightRepository.save(flight); }
    public Seats addSeats(Seats seat){return seatRepository.save(seat); }
    public User addEmployee(User user){return userRepository.save(user); }
    public User addTraveler(User user){return userRepository.save(user); }

    //Delete Methods
    public void deleteAirport(String id){airportRepository.deleteById(id);}
    public void deleteFlight(Integer id){flightRepository.deleteById(id);}
    public void deleteSeats(Integer id){seatRepository.deleteById(id);}
    public void deleteEmployee(Integer id){userRepository.deleteEmployee(id);}
    public void deleteTraveler(Integer id){userRepository.deleteTraveler(id);}

    //update methods
    public String updateFlight(Flight flight)  {

        try {
            if (flight.getId() == null) throw new Exception();

            Flight temp = flightRepository.findById(flight.getId()).get();

            if (flight.getRoute() == null) flight.setRoute(temp.getRoute());

            if (flight.getAirplane() == null) flight.setAirplane(temp.getAirplane());

            if (flight.getDepartTime() == null) flight.setDepartTime(temp.getDepartTime());

            if (flight.getReservedSeats() == null) flight.setReservedSeats(temp.getReservedSeats());

            if (flight.getSeatPrice() == null) flight.setSeatPrice(temp.getSeatPrice());

            flightRepository.save(flight);
        }
        catch(NoSuchElementException e){
            return "no flight with this id is present, unable to perform update";
        }
        catch(Exception e){
            return "id was not provided, unable to perform update";
        }
        return "update successful";
    }

    public String updateSeats(Seats seat) {
        try {
            if(seat.getId() == null) throw new Exception();

            Seats temp = seatRepository.findById(seat.getId()).get();

            if(seat.getFlight() == null) seat.setFlight(flightRepository.findById(seat.getId()).get());

            if(seat.getFirst() == null) seat.setFirst(temp.getFirst());

            if(seat.getBusiness() == null) seat.setBusiness(temp.getBusiness());

            if(seat.getEconomy() == null) seat.setEconomy(temp.getEconomy());

            seatRepository.save(seat);
        }
        catch(NoSuchElementException e){
            return "no flight with this id is present, unable to perform update";
        }
        catch(Exception e){
            return "id was not provided, unable to perform update";
        }

        return "update successful";
    }

    public String updateAirport(Airport a) {
        return null;
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
        try {
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
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "Booking could not be added.";
        }
    }

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
            Booking booking = new Booking();
            booking.setId(existingBooking.getId());
            booking.setIsActive(existingBooking.getIsActive());
            if (null != bdto.getConfirmationCode()) {
                booking.setConfirmationCode(bdto.getConfirmationCode());
            }
            else {
                booking.setConfirmationCode(existingBooking.getConfirmationCode());
            }

            BookingPayment bookingPayment =
                    bookingPaymentDAO.getBookingPaymentByBooking(existingBooking);
            if (null != bdto.getStripeId()) {
                bookingPayment.setStripeId(bdto.getStripeId());
            }
            FlightBooking flightBooking =
                    flightBookingDAO.getFlightBookingByBooking(existingBooking);
            if (null != bdto.getFlightId()) {
                Flight f = fdao.getFlightFromId(bdto.getFlightId());
                flightBooking.setFlight(f);
            }

            bdao.addBooking(booking);
            bookingPaymentDAO.addBookingPayment(bookingPayment);
            flightBookingDAO.addFlightBooking(flightBooking);

            if (null != bdto.getUserId() || null != bdto.getBookingGuest()) {
                // delete the old booking_agent/user/guest
                bookingAgentDAO.deleteBookingAgent(new BookingAgent(booking, null));
                bookingUserDAO.deleteBookingUser(new BookingUser(null, booking));
                bookingGuestDAO.deleteBookingGuest(new BookingGuest(booking,
                        null, null));
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
            }
            return "Booking updated.";
        }catch(Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "Could not update booking.";
        }
    }

    public String deleteBooking(int id) {
        try {
            Booking b = new Booking();
            b.setId(id);
            bdao.deleteBooking(b);
            bookingPaymentDAO.deleteBookingPayment(new BookingPayment(null, null, b));
            flightBookingDAO.deleteFlightBooking(new FlightBooking(null, b));
            bookingAgentDAO.deleteBookingAgent(new BookingAgent(b, null));
            bookingUserDAO.deleteBookingUser(new BookingUser(null, b));
            bookingGuestDAO.deleteBookingGuest(new BookingGuest(b, null, null));
            passengerDAO.deletePassenger(new Passenger(b, null, null,
                    null, null, null));
            return "Booking deleted.";
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "Could not delete booking.";
        }
    }

    public String cancelBooking(int id) {
        try {
            Booking b = bdao.getBookingById(id);
            if (!b.getIsActive()) {
                return "Booking is already canceled.";
            }
            BookingPayment bp = bookingPaymentDAO.getBookingPaymentByBooking(b);
            b.setIsActive(false);
            bp.setRefunded(true);
            bdao.updateBooking(b);
            bookingPaymentDAO.updateBookingPayment(bp);
            return "Booking is canceled.";
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "Could not cancel booking.";
        }
    }

    public String uncancelBooking(int id) {
        try {
            Booking b = bdao.getBookingById(id);
            if (b.getIsActive()) {
                return "Booking is already active.";
            }
            BookingPayment bp = bookingPaymentDAO.getBookingPaymentByBooking(b);
            b.setIsActive(true);
            bp.setRefunded(false);
            bdao.updateBooking(b);
            bookingPaymentDAO.updateBookingPayment(bp);
            return "Booking is no longer canceled.";
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "Could not uncancel booking.";
        }
    }

    public List<Passenger> getPassengers() {
        List<Passenger> passengers = null;
        try {
            passengers = passengerDAO.getAllPassengers();
            for (Passenger p : passengers) {
                Booking b = bdao.getBookingById(p.getBooking().getId());
                p.setBooking(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return passengers;
    }

    public Passenger getPassengerById(int id) {
        Passenger p = null;
        try {
            p = passengerDAO.getPassenger(id);
            Booking b = bdao.getBookingById(p.getBooking().getId());
            p.setBooking(b);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return p;
    }

    public List<Passenger> getPassengersByBookingId(int id) {
        List<Passenger> passengers = null;
        try {
            Booking b = bdao.getBookingById(id);
            passengers = passengerDAO.getPassengersByBooking(b);
            for (Passenger p : passengers) {
                p.setBooking(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return passengers;
    }

    public String addPassenger(Passenger p) {
        try {
            int status = passengerDAO.addPassenger(p);
            if (1 == status) {
                return "Passenger added.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return "Could not add passenger.";
    }

    public String updatePassenger(Passenger p) {
        try {
            Passenger existingPassenger = passengerDAO.getPassenger(p.getId());
            if (p.getGiven_name() == null) { p.setGiven_name(existingPassenger.getGiven_name()); }
            if (p.getFamily_name() == null) { p.setFamily_name(existingPassenger.getFamily_name()); }
            if (p.getDate() == null) { p.setDate(existingPassenger.getDate()); }
            if (p.getGender() == null) { p.setGender(existingPassenger.getGender()); }
            if (p.getAddress() == null) { p.setAddress(existingPassenger.getAddress()); }
            int result = passengerDAO.updatePassenger(p);
            if (result == 1) {
                return "Passenger updated.";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Passenger could not be updated.";
    }

    public String deletePassenger(int id) {
        try {
            Passenger p = new Passenger();
            p.setId(id);
            int status = passengerDAO.deletePassenger(p);
            if (1 == status) {
                return "Passenger deleted.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return "Could not delete Passenger.";
    }
}
