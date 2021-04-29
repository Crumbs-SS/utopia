package com.ss.utopia.dao;

import com.ss.utopia.entity.Flight;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightDAO extends BaseDAO implements ResultSetExtractor<List<Flight>> {

    public Integer addFlight(Flight flight) throws SQLException, ClassNotFoundException {
        Integer key = getAllFlights().get(getAllFlights().size() - 1).getId() + 1;
        int result = jdbcTemplate.update("INSERT INTO flight(id, route_id, airplane_id, departure_time, reserved_seats," +
                " seat_price) VALUES(?, ?, ?, ?, ?, ?)",
                key,
                flight.getRoute().getId(),
                flight.getAirplane().getId(),
                flight.getDepartTime(),
                flight.getReservedSeats(),
                flight.getSeatPrice());

        flight.setId(key);
        return result;
    }

    public int updateFlight(Flight flight) throws DataAccessException {
        int result = jdbcTemplate.update("UPDATE flight SET route_id = ?, airplane_id = ?, departure_time = ?, reserved_seats = ?, " +
                "seat_price = ? WHERE id = ?", flight.getRoute().getId(),
                flight.getAirplane().getId(),
                flight.getDepartTime(),
                flight.getReservedSeats(),
                flight.getSeatPrice(),
                flight.getId());

        return result;
    }

    public int deleteFlight(Flight flight) throws SQLException, ClassNotFoundException{
        return jdbcTemplate.update("DELETE FROM flight WHERE id = ?", flight.getId());
    }

    public Flight getFlightFromId(Integer id) throws SQLException, ClassNotFoundException {
        List<Flight> flights = jdbcTemplate.query("SELECT * FROM flight WHERE id = ?", new Object[]{id},this);

        if(flights != null && flights.size() > 0)
            return flights.get(0);
        return null;
    }

    public List<Flight> getAllFlights() throws SQLException, ClassNotFoundException{
        return jdbcTemplate.query("SELECT * FROM flight", this);
    }

    @Override
    public List<Flight> extractData(ResultSet rs) throws SQLException {
        List<Flight> flights = new ArrayList<>();

        while(rs.next())
            flights.add(Flight.toObject(rs));

        return flights;
    }
}
