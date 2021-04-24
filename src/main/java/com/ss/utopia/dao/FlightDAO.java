package com.ss.utopia.dao;

import com.ss.utopia.entity.Flight;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO extends BaseDAO<Flight>{

    public FlightDAO(Connection conn) {
        super(conn);
    }

    public Integer addFlight(Flight flight) throws SQLException, ClassNotFoundException {
        Integer key = getAllFlights().get(getAllFlights().size() - 1).getId() + 1;
        save("INSERT INTO flight(id, route_id, airplane_id, departure_time, reserved_seats," +
                " seat_price) VALUES(?, ?, ?, ?, ?, ?)",
                new Object[]{
                        key,
                        flight.getRoute().getId(),
                        flight.getAirplane().getId(),
                        flight.getDepartTime(),
                        flight.getReservedSeats(),
                        flight.getSeatPrice()
        });

        flight.setId(key);
        return key;
    }

    public void updateFlight(Flight flight) throws SQLException, ClassNotFoundException{
        save("UPDATE flight SET route_id = ?, airplane_id = ?, departure_time = ?, reserved_seats = ?, " +
                "seat_price = ? WHERE id = ?", new Object[]{
           flight.getRoute().getId(),
           flight.getAirplane().getId(),
           flight.getDepartTime(),
           flight.getReservedSeats(),
           flight.getSeatPrice(),
                flight.getId()
        });
    }

    public void deleteFlight(Flight flight) throws SQLException, ClassNotFoundException{
        save("DELETE FROM flight WHERE id = ?", new Object[]{flight.getId()});
    }

    public Flight getFlightFromId(Integer id) throws SQLException, ClassNotFoundException {
        List<Flight> flights = read("SELECT * FROM flight WHERE id = ?", new Object[]{id});

        if(flights.size() > 0)
            return flights.get(0);
        return null;
    }

    public List<Flight> getAllFlights() throws SQLException, ClassNotFoundException{
        return read("SELECT * FROM flight", null);
    }

    @Override
    public List<Flight> extractData(ResultSet rs) throws SQLException {
        List<Flight> flights = new ArrayList<>();

        while(rs.next())
            flights.add(Flight.toObject(rs));

        return flights;
    }
}
