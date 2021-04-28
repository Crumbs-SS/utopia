package com.ss.utopia.service;


import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            fdao.addFlight(f);
            return "flight added";
        } catch (Exception e) {
            e.printStackTrace();
            return "flight not added";
        }
    }

    // TODO
    public Flight updateFlight(Flight f) {
        return null;
    }

    public String deleteFlight(int flightId) {
        try {
            fdao.deleteFlight(new Flight(flightId));
            return "Flight with id=" + flightId + " deleted.";
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
            airportDAO.addAirport(a);
            return "Airport with code=" + a.getAirportCode() + " added.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Airport could not be added.";
        }
    }

    public String updateAirport(Airport a) {
        try {
            airportDAO.updateAirport(a);
            return "Airport with code=" + a.getAirportCode() + " updated.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Airport could not be updated.";
        }
    }

    public String deleteAirport(String airportId) {
        try {
            airportDAO.deleteAirport(new Airport(airportId, "ph"));
            return "Airport with id=" + airportId + " deleted.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Airport could not be deleted";
        }
    }
}
