package com.ss.utopia.dao;

import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Route;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportDAO extends BaseDAO implements ResultSetExtractor<List<Airport>> {

    public void addAirport(Airport airport) {
        jdbcTemplate.update("INSERT INTO airport(iata_id, city) VALUES(?, ?)",
                airport.getAirportCode(),
                airport.getCityName());
    }
    public void updateAirport(Airport airport) {
       jdbcTemplate.update("UPDATE airport SET city = ? WHERE iata_id = ?",
               airport.getCityName(), airport.getAirportCode());
    }

    public void deleteAirport(Airport airport) {
        jdbcTemplate.update("DELETE FROM airport WHERE iata_id = ?", airport.getAirportCode());
    }

    public Airport getAirportByAirportCode(String airportCode) {
        List<Airport> airports = jdbcTemplate.query("SELECT * FROM airport WHERE iata_id = ?", new Object[]{airportCode},this);

        if(airports != null && airports.size() > 0)
            return airports.get(0);

        return null;
    }

    public List<Airport> getAllAirports()  {
        return jdbcTemplate.query("SELECT * FROM airport", this);
    }

    @Override
    public List<Airport> extractData(ResultSet rs) throws SQLException {
        List<Airport> airports = new ArrayList<>();

        while(rs.next()){
            airports.add(Airport.toObject(rs));
        }

        return airports;
    }
}
