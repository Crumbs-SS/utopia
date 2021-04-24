package com.ss.utopia.dao;

import com.ss.utopia.entity.Airport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportDAO extends BaseDAO<Airport>{
    public AirportDAO(Connection conn) {
        super(conn);
    }


    public void addAirport(Airport airport) throws SQLException, ClassNotFoundException {
        save("INSERT INTO airport(iata_id, city) VALUES(?, ?)",
                new Object[]{
                        airport.getAirportCode(),
                        airport.getCityName()
                });
    }
    public void updateAirport(Airport airport) throws SQLException, ClassNotFoundException {
       save("UPDATE airport SET city = ? WHERE iata_id = ?",
               new Object[] {airport.getCityName(), airport.getAirportCode()});
    }

    public void deleteAirport(Airport airport) throws SQLException, ClassNotFoundException {
        save("DELETE FROM airport WHERE iata_id = ?", new Object[]{airport.getAirportCode()});
    }

    public Airport getAirportByAirportCode(String airportCode) throws SQLException, ClassNotFoundException {
        List<Airport> airports = read("SELECT * FROM airport WHERE iata_id = ?", new Object[]{airportCode});
        if(airports.size() > 0)
            return airports.get(0);

        return null;
    }

    public List<Airport> getAllAirports() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM airport", null);
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
