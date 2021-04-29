package com.ss.utopia.service;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.dao.SeatDAO;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional(rollbackFor = { Exception.class })
public class EmployeeService {

    @Autowired
    FlightDAO flightDAO;

    @Autowired
    RouteDAO routeDAO;

    @Autowired
    AirplaneDAO airplaneDAO;

    @Autowired
    SeatDAO seatDAO;

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
            if(temp == null) throw new Exception("id was not provided");

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
    public List<Seat> getSeats() {
        List<Seat> seats = null;
        try {
            seats = seatDAO.getAllSeats();

            for (Seat seat : seats) {
                Flight temp = flightDAO.getFlightFromId(seat.getFlight().getId());
                temp.setRoute(routeDAO.getRouteById(seat.getFlight().getId()));
                temp.setAirplane(airplaneDAO.getAirplaneById(seat.getFlight().getId()));
                seat.setFlight(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return seats;

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

}
