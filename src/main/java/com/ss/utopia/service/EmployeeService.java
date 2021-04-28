package com.ss.utopia.service;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@Transactional(rollbackFor = { Exception.class })
public class EmployeeService {

    @Autowired
    FlightDAO flightDAO;

    @Autowired
    RouteDAO routeDAO;

    @Autowired
    AirplaneDAO airplaneDAO;

    public Flight getFlight(String id){
        Integer flightID = Integer.parseInt(id);
        Flight flight = null;
        try {
            flight = flightDAO.getFlightFromId(flightID);
            flight.setRoute(routeDAO.getRouteById(flight.getRoute().getId()));
            flight.setAirplane(airplaneDAO.getAirplaneById(flight.getAirplane().getId()));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return flight;

    }
    public void updateFlightRoute(String id, Integer newRouteID) {
        Integer flightID = Integer.parseInt(id);
        Flight flight = null;
        try {
            flight = flightDAO.getFlightFromId(flightID);
            flight.setRoute(routeDAO.getRouteById(newRouteID));
            flight.setAirplane(airplaneDAO.getAirplaneById(flight.getAirplane().getId()));
            flightDAO.updateFlight(flight);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateFlightAirplane(String id, Integer newAirplaneID){
        Integer flightID = Integer.parseInt(id);
        Flight flight = null;
        try {
            flight = flightDAO.getFlightFromId(flightID);
            flight.setRoute(routeDAO.getRouteById(flight.getRoute().getId()));
            flight.setAirplane(airplaneDAO.getAirplaneById(newAirplaneID));
            flightDAO.updateFlight(flight);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateFlightDepartTime(String id, Timestamp newDepartTime){
        Integer flightID = Integer.parseInt(id);
        Flight flight = null;
        try {
            flight = flightDAO.getFlightFromId(flightID);
            flight.setRoute(routeDAO.getRouteById(flight.getRoute().getId()));
            flight.setAirplane(airplaneDAO.getAirplaneById(flight.getAirplane().getId()));
            flight.setDepartTime(newDepartTime);
            flightDAO.updateFlight(flight);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateFlightSeats(String id, Integer newReservedSeats){
        Integer flightID = Integer.parseInt(id);
        Flight flight = null;
        try {
            flight = flightDAO.getFlightFromId(flightID);
            flight.setRoute(routeDAO.getRouteById(flight.getRoute().getId()));
            flight.setAirplane(airplaneDAO.getAirplaneById(flight.getAirplane().getId()));
            flight.setReservedSeats(newReservedSeats);
            flightDAO.updateFlight(flight);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateFlightSeatPrice(String id, Float newSeatPrice){
        Integer flightID = Integer.parseInt(id);
        Flight flight = null;
        try {
            flight = flightDAO.getFlightFromId(flightID);
            flight.setRoute(routeDAO.getRouteById(flight.getRoute().getId()));
            flight.setAirplane(airplaneDAO.getAirplaneById(flight.getAirplane().getId()));
            flight.setSeatPrice(newSeatPrice);
            flightDAO.updateFlight(flight);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateFlight(String id, Integer routeID, Integer airplaneID, Timestamp departTime, Integer reservedSeats, Float seatPrice)
    {
        Integer flightID = Integer.parseInt(id);
        Flight flight = null;
        try {
            flight = flightDAO.getFlightFromId(flightID);

            if (routeID != null)
                flight.setRoute(routeDAO.getRouteById(routeID));
            else
                flight.setRoute(routeDAO.getRouteById(flight.getRoute().getId()));

            if(airplaneID != null)
                flight.setAirplane(airplaneDAO.getAirplaneById(airplaneID));
            else
                flight.setAirplane(airplaneDAO.getAirplaneById(flight.getAirplane().getId()));

            if(departTime != null)
                flight.setDepartTime(departTime);
            if(reservedSeats != null)
                flight.setReservedSeats(reservedSeats);
            if(seatPrice != null)
                flight.setSeatPrice(seatPrice);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
