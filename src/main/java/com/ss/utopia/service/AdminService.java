package com.ss.utopia.service;

import com.ss.utopia.dto.BookingDTO;
import com.ss.utopia.entity.*;
import com.ss.utopia.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional(rollbackFor = { Exception.class })
public class AdminService {

    @Autowired AirportRepository airportRepository;
    @Autowired FlightRepository flightRepository;
    @Autowired AirplaneRepository airplaneRepository;
    @Autowired RouteRepository routeRepository;
    @Autowired SeatRepository seatRepository;
    @Autowired UserRepository userRepository;
    @Autowired BookingRepository bookingRepository;
    @Autowired BookingPaymentRepository bookingPaymentRepository;
    @Autowired FlightBookingRepository flightBookingRepository;
    @Autowired PassengerRepository passengerRepository;
    @Autowired BookingUserRepository bookingUserRepository;
    @Autowired BookingAgentRepository bookingAgentRepository;
    @Autowired BookingGuestRepository bookingGuestRepository;

    final int TRAVELER = 2;
    final int EMPLOYEE = 3;

    //Read Methods
    public List<Airport> getAirports() {
        try {
            return airportRepository.findAll();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public List<Flight> getFlights() {
        try {
            return flightRepository.findAll();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }

    }

    public List<Seats> getSeats() {
        try {
            return seatRepository.findAll();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public List<User> getTravelers() {
        try {
            return userRepository.getTravellers();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public List<User> getEmployees() {
        try {
            return userRepository.getEmployees();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public List<Booking> getBookings() {
        try {
            return bookingRepository.findAll();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public List<Passenger> getPassengers() {
        try {
            return passengerRepository.findAll();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public Passenger getPassengerById(int id) {
        try {
            return passengerRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public List<Passenger> getPassengersByBookingId(int id) {
        try {
            Booking b = new Booking();
            b.setId(id);
            return passengerRepository.getPassengersByBooking(b);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    //Add Methods
    public Airport addAirport(Airport airport) {
        try {
            if (null == airport.getCityName()) { return null; }
            return airportRepository.save(airport);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public Flight addFlight(Flight flight) {
        try {
            // just to check if the string was a valid datetime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime timestamp = LocalDateTime.parse(flight.getDepartTime(), formatter);

            Airplane airplane = airplaneRepository.findById(flight.getAirplane().getId()).orElseThrow();
            flight.setAirplane(airplane);
            Airport ori = airportRepository
                    .findById(flight.getRoute().getOriAirport().getAirportCode()).orElseThrow();
            Airport des = airportRepository
                    .findById(flight.getRoute().getDesAirport().getAirportCode()).orElseThrow();
            Route route = routeRepository.findByOriAirportAndDesAirport(ori, des);
            if (null == route) {
                route = routeRepository.save(new Route(ori, des));
            }
            flight.setRoute(route);
            return flightRepository.save(flight);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }
/*
    public Seats addSeats(int flightId, Seats seat) {
        try {
            Flight f = flightRepository.findById(flightId).orElseThrow();
            seat.setFlight(f);
            return seatRepository.save(seat);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }
*/
    public User addEmployee(User user) {
        try {
            user.setUserRole(new UserRole(EMPLOYEE, "ph"));
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public User addTraveler(User user) {
        try {
            user.setUserRole(new UserRole(TRAVELER, "ph"));
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public Booking addBooking(BookingDTO bdto) {
        try {
            if (null == bdto.getUserId() && null == bdto.getBookingGuest()) {
                return null;
            }
            String confirmationCode = "CONFIRMATION-" + (bookingRepository.findAll().size() + 1);
            Booking booking = bookingRepository.saveAndFlush(new Booking(true, confirmationCode));

            Flight flight = flightRepository.findById(bdto.getFlightId()).orElseThrow();
            BookingPayment bookingPayment = new BookingPayment(booking, bdto.getStripeId(), false);
            bookingPaymentRepository.save(bookingPayment);
            flightBookingRepository.save(new FlightBooking(flight, booking));

            if (null != bdto.getUserId()) {
                User user = userRepository.findById(bdto.getUserId()).orElseThrow();
                UserRole role = user.getUserRole();
                if (role.getName().equals("ADMIN")) {
                    throw new IllegalArgumentException();
                } else if (role.getName().equals("AGENT")) {
                    bookingAgentRepository.save(new BookingAgent(booking, user));
                } else if (role.getName().equals("CUSTOMER")) {
                    bookingUserRepository.save(new BookingUser(booking, user));
                }
            } else {
                bdto.getBookingGuest().setBooking(booking);
                bookingGuestRepository.save(bdto.getBookingGuest());
            }
            return booking;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public Passenger addPassenger(int id, Passenger p) {
        try {
            Booking b = bookingRepository.findById(id).orElseThrow();
            p.setBooking(b);
            return passengerRepository.save(p);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }


    //Delete Methods
    public String deleteAirport(String id) {
        try {
            airportRepository.deleteById(id);
            return "Airport " + id + " deleted.";
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public String deleteFlight(Integer id) {
        try {
            flightRepository.deleteById(id);
            return "Flight " + id + " deleted.";
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public String deleteSeats(Integer id) {
        try {
            seatRepository.deleteById(id);
            return "Seats with id=" + id + " deleted.";
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public String deleteEmployee(Integer id) {
        try {
            userRepository.deleteEmployee(id);
            return "Employee " + id + " deleted.";
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public String deleteTraveler(Integer id) {
        try {
            userRepository.deleteTraveler(id);
            return "Traveler " + id + " deleted.";
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public String deleteBooking(int id) {
        try {
            Booking b = new Booking();
            b.setId(id);
            bookingRepository.delete(b);
            /*
            bookingPaymentRepository.delete(new BookingPayment(b, null, true));
            flightBookingRepository.delete(new FlightBooking(null, b));
            bookingAgentRepository.delete(new BookingAgent(b, null));
            bookingUserRepository.delete(new BookingUser(null, b));
            bookingGuestRepository.delete(new BookingGuest(b, null, null));
            passengerRepository.delete(new Passenger(b, null, null,
                    null, null, null));

             */
            return "Booking deleted.";
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public String deletePassenger(int id) {
        try {
            Passenger p = new Passenger();
            p.setId(id);
            passengerRepository.delete(p);
            return "Passenger deleted.";
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }


    //update methods
    public Flight updateFlight(int id, Flight flight) {
        try {
            flight.setId(id);
            Flight temp = flightRepository.findById(flight.getId()).get();
            if (flight.getRoute() == null) flight.setRoute(temp.getRoute());
            if (flight.getAirplane() == null) flight.setAirplane(temp.getAirplane());
            if (flight.getDepartTime() == null) flight.setDepartTime(temp.getDepartTime());
            if (flight.getReservedSeats() == null) flight.setReservedSeats(temp.getReservedSeats());
            if (flight.getSeatPrice() == null) flight.setSeatPrice(temp.getSeatPrice());
            return flightRepository.save(flight);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public Seats updateSeats(int id, Seats seat) {
        try {
            seat.setId(id);
            Seats temp = seatRepository.findById(seat.getId()).get();
            seat.setFlight(temp.getFlight());
            if (seat.getFirst() == null) seat.setFirst(temp.getFirst());
            if (seat.getBusiness() == null) seat.setBusiness(temp.getBusiness());
            if (seat.getEconomy() == null) seat.setEconomy(temp.getEconomy());
            return seatRepository.save(seat);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public Airport updateAirport(String airportCode, Airport a) {
        try {
            a.setAirportCode(airportCode);
            airportRepository.findById(a.getAirportCode()).get();
            return airportRepository.save(a);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public User updateTraveler(int id, User t) {
        try {
            t.setId(id);
            t.setUserRole(new UserRole(TRAVELER, "ph"));
            User oldT = userRepository.findById(t.getId()).get();
            if (t.getGivenName() == null) { t.setGivenName(oldT.getGivenName()); }
            if (t.getFamilyName() == null) {  t.setFamilyName(oldT.getFamilyName()); }
            if (t.getUsername() == null) { t.setUsername(oldT.getUsername());  }
            if (t.getEmail() == null) { t.setEmail(oldT.getEmail()); }
            if (t.getPassword() == null) { t.setPassword(oldT.getPassword()); }
            if (t.getPhone() == null) { t.setPhone(oldT.getPhone()); }
            return userRepository.save(t);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public User updateEmployee(int id, User e) {
        try {
            e.setId(id);
            e.setUserRole(new UserRole(EMPLOYEE, "ph"));
            User oldE = userRepository.findById(e.getId()).get();
            if (e.getGivenName() == null) { e.setGivenName(oldE.getGivenName()); }
            if (e.getFamilyName() == null) { e.setFamilyName(oldE.getFamilyName()); }
            if (e.getUsername() == null) { e.setUsername(oldE.getUsername()); }
            if (e.getEmail() == null) { e.setEmail(oldE.getEmail()); }
            if (e.getPassword() == null) { e.setPassword(oldE.getPassword()); }
            if (e.getPhone() == null) { e.setPhone(oldE.getPhone()); }
            return userRepository.save(e);
        } catch (Exception ex) {
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }


    public Booking updateBooking(int id, BookingDTO bdto) {
        // let them change associated flight
        // let them change stripe_id
        // let them change confirmation code
        // let them change booking agent/user/guest
        try {
            bdto.setId(id);
            Booking oldB = bookingRepository.findById(bdto.getId()).get();
            Booking booking = new Booking();
            booking.setId(oldB.getId());
            booking.setActive(oldB.getActive());
            if (null != bdto.getConfirmationCode()) {
                booking.setConfirmationCode(bdto.getConfirmationCode());
            } else {
                booking.setConfirmationCode(oldB.getConfirmationCode());
            }

            BookingPayment bookingPayment =
                    bookingPaymentRepository.getBookingPaymentByBooking(oldB);
            if (null != bdto.getStripeId()) {
                bookingPayment.setStripeId(bdto.getStripeId());
            }
            FlightBooking flightBooking =
                    flightBookingRepository.findByBooking(oldB).get(0);
            if (null != bdto.getFlightId()) {
                Flight f = flightRepository.findById(bdto.getFlightId()).get();
                flightBooking.setFlight(f);
            }

            booking = bookingRepository.save(booking);
            bookingPaymentRepository.save(bookingPayment);
            flightBookingRepository.save(flightBooking);

            if (null != bdto.getUserId() || null != bdto.getBookingGuest()) {
                // delete the old booking_agent/user/guest
                bookingAgentRepository.delete(new BookingAgent(booking, null));
                bookingUserRepository.delete(new BookingUser(null, booking));
                bookingGuestRepository.delete(new BookingGuest(booking,
                        null, null));
                if (null != bdto.getUserId()) {
                    User user = userRepository.findById(bdto.getUserId()).get();
                    UserRole role = user.getUserRole();
                    if (role.getName().equals("ADMIN")) {
                        throw new IllegalArgumentException();
                    } else if (role.getName().equals("AGENT")) {
                        bookingAgentRepository.save(new BookingAgent(booking, user));
                    } else if (role.getName().equals("CUSTOMER")) {
                        bookingUserRepository.save(new BookingUser(user, booking));
                    }
                } else {
                    bdto.getBookingGuest().setBooking(booking);
                    bookingGuestRepository.save(bdto.getBookingGuest());
                }
            }
            return booking;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public Passenger updatePassenger(int id, Passenger p) {
        try {
            p.setId(id);
            Passenger oldP = passengerRepository.findById(p.getId()).get();
            if (p.getGivenName() == null) { p.setGivenName(oldP.getGivenName()); }
            if (p.getFamilyName() == null) { p.setFamilyName(oldP.getFamilyName()); }
            if (p.getDate() == null) { p.setDate(oldP.getDate()); }
            if (p.getGender() == null) { p.setGender(oldP.getGender()); }
            if (p.getAddress() == null) { p.setAddress(oldP.getAddress()); }
            return passengerRepository.save(p);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    // Activate/Cancel Booking methods
    public Booking cancelBooking(int id) {
        try {
            Booking b = bookingRepository.findById(id).get();
            if (!b.getActive()) {
                return b;
            }
            BookingPayment bp = bookingPaymentRepository.findById(id).get();
            b.setActive(false);
            bp.setRefunded(true);
            b = bookingRepository.save(b);
            bookingPaymentRepository.save(bp);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public Booking uncancelBooking(int id) {
        try {
            Booking b = bookingRepository.findById(id).get();
            if (b.getActive()) {
                return b;
            }
            BookingPayment bp = bookingPaymentRepository.findById(id).get();
            b.setActive(true);
            bp.setRefunded(false);
            b = bookingRepository.save(b);
            bookingPaymentRepository.save(bp);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }
}

