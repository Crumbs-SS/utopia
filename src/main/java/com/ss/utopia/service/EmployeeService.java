package com.ss.utopia.service;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

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

    public void updateFlight(Flight flight)  {

        try {
            if(flight.getRoute() == null)
                flight.setRoute(flightDAO.getFlightFromId(flight.getId()).getRoute());

            if(flight.getAirplane() == null)
                flight.setAirplane(flightDAO.getFlightFromId(flight.getId()).getAirplane());

            if(flight.getDepartTime() == null)
                flight.setDepartTime(flightDAO.getFlightFromId(flight.getId()).getDepartTime());

            if(flight.getReservedSeats() == null)
                flight.setReservedSeats(flightDAO.getFlightFromId(flight.getId()).getReservedSeats());

            if(flight.getSeatPrice() == null)
                flight.setSeatPrice(flightDAO.getFlightFromId(flight.getId()).getSeatPrice());

            flightDAO.updateFlight(flight);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
