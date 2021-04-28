package com.ss.utopia.dao;

import com.ss.utopia.entity.Airport;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AirportDAO extends BaseDAO implements ResultSetExtractor<List<Airport>> {

    public int addAirport(Airport airport) throws DataAccessException {
        return jdbcTemplate.update("INSERT INTO airport(iata_id, city) VALUES(?, ?)",
                airport.getAirportCode(),
                airport.getCityName());
    }
    public int updateAirport(Airport airport) {
       return jdbcTemplate.update("UPDATE airport SET city = ? WHERE iata_id = ?",
               airport.getCityName(), airport.getAirportCode());
    }

    public int deleteAirport(Airport airport) throws DataAccessException {
        return jdbcTemplate.update("DELETE FROM airport WHERE iata_id = ?", airport.getAirportCode());
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
