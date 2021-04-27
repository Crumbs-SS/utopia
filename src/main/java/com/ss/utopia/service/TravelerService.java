package com.ss.utopia.service;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TravelerService {

    @Autowired
    FlightDAO flightDAO;
    @Autowired
    AirplaneDAO airplaneDAO;
    @Autowired
    RouteDAO routeDAO;
    @Autowired
    UserDAO userDAO;

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

    // AddBooking
        //-> booking_user
        //-> booking_payment
        //-> flight_booking

    // DeleteBooking
        //-> flight_booking
        //-> booking_payment
        //-> booking_user
}
