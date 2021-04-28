package com.ss.utopia.service;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Seat;
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
            Flight temp = flightDAO.getFlightFromId(flight.getId());

            if(flight.getRoute() == null)
                flight.setRoute(temp.getRoute());

            if(flight.getAirplane() == null)
                flight.setAirplane(temp.getAirplane());

            if(flight.getDepartTime() == null)
                flight.setDepartTime(temp.getDepartTime());

            if(flight.getReservedSeats() == null)
                flight.setReservedSeats(temp.getReservedSeats());

            if(flight.getSeatPrice() == null)
                flight.setSeatPrice(temp.getSeatPrice());

            flightDAO.updateFlight(flight);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSeats(Seat seat) {
    }
}
