package com.ss.utopia.service;

import com.ss.utopia.dao.*;
import com.ss.utopia.dto.BookingDTO;
import com.ss.utopia.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional(rollbackFor = { Exception.class })
public class TravelerService {

    @Autowired
    FlightDAO flightDAO;
    @Autowired
    AirplaneDAO airplaneDAO;
    @Autowired
    RouteDAO routeDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    BookingDAO bookingDAO;
    @Autowired
    BookingUserDAO bookingUserDAO;
    @Autowired
    BookingPaymentDAO bookingPaymentDAO;
    @Autowired
    FlightBookingDAO flightBookingDAO;
    @Autowired
    PassengerDAO passengerDAO;


    public List<Flight> getFlights() {
        List<Flight> flights = null;
        try {
            flights = flightDAO.getAllFlights();

            for (Flight flight : flights) {
                flight.setRoute(routeDAO.getRouteById(flight.getRoute().getId()));
                flight.setAirplane(airplaneDAO.getAirplaneById(flight.getAirplane().getId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flights;
    }

    public User getUser(String id){
        Integer userId = Integer.parseInt(id);
        User user = null;

        try{
            user = userDAO.getUserById(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public User login(User body){
        User user = null;
        try{
            user = userDAO.getUserByCred(body.getUsername(), body.getPassword());
        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public Booking addBooking(BookingDTO bookingDTO){
        Booking booking = null;
        try{
            int amountOfBookings = bookingDAO.getAllBookings().size();
            booking = new Booking(true, "CONFIRMATION-" + amountOfBookings);

            User user = userDAO.getUserById(bookingDTO.getUserId());
            Flight flight = flightDAO.getFlightFromId(bookingDTO.getFlightId());
            String stripeId = bookingDTO.getStripeId();

            bookingDAO.addBooking(booking);
            booking = bookingDAO.getBookingByCode(booking.getConfirmationCode());

            bookingUserDAO.addBookingUser(new BookingUser(user, booking));
            bookingPaymentDAO.addBookingPayment(new BookingPayment(stripeId, false, booking));
            flightBookingDAO.addFlightBooking(new FlightBooking(flight, booking));

            setBookingsForPassengers(bookingDTO.getPassengerIds(), booking);
        }catch(Exception e){
            e.printStackTrace();
        }

        return booking;
    }

    public void cancelBooking(String bookingId){
        try{
            Integer id = Integer.parseInt(bookingId);
            Booking booking = bookingDAO.getBookingById(id);
            if(booking != null){
                BookingPayment bookingPayment = bookingPaymentDAO.getBookingPaymentByBooking(booking);

                booking.setIsActive(false);
                bookingPayment.setRefunded(true);

                bookingDAO.updateBooking(booking);
                bookingPaymentDAO.updateBookingPayment(bookingPayment);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setBookingsForPassengers(List<Integer> passengerIds, Booking booking) throws SQLException, ClassNotFoundException {
        for (Integer passengerId : passengerIds) {
            passengerDAO.getPassenger(passengerId).setBooking(booking);
        }
    }
}
