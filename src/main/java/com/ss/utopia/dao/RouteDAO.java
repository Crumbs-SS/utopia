package com.ss.utopia.dao;

import com.ss.utopia.entity.Route;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RouteDAO extends BaseDAO implements ResultSetExtractor<List<Route>> {

    public Integer addRoute(Route route) throws SQLException, ClassNotFoundException {
        return jdbcTemplate.update("INSERT INTO route(origin_id, destination_id) VALUES(?, ?)", route.getOriAirport().getAirportCode(),
                route.getDesAirport().getAirportCode());
    }
    public void updateRoute(Route route) throws SQLException, ClassNotFoundException {
        jdbcTemplate.update("UPDATE route SET origin_id = ?, destination_id = ? WHERE id = ?", route.getOriAirport().getAirportCode(),
                route.getDesAirport().getAirportCode(),
                route.getId());
    }
    public void deleteRoute(Route route) throws SQLException, ClassNotFoundException {
        jdbcTemplate.update("DELETE FROM route WHERE id = ?", route.getId());
    }

    public List<Route> getAllRoutes() throws SQLException, ClassNotFoundException {
        return jdbcTemplate.query("SELECT id, origin_id, destination_id, origin_airport.city AS origin_city, destination_airport.city AS destination_city FROM route\n" +
                "LEFT JOIN airport origin_airport ON origin_id = origin_airport.iata_id\n" +
                "LEFT JOIN airport destination_airport ON destination_id = destination_airport.iata_id", this);
    }

    public Route getRouteById(Integer id) throws SQLException, ClassNotFoundException{
       List<Route> routes = jdbcTemplate.query("SELECT id, origin_id, destination_id, origin_airport.city AS origin_city, destination_airport.city AS destination_city FROM route \n" +
                "JOIN airport origin_airport ON origin_id = origin_airport.iata_id\n" +
                "JOIN airport destination_airport ON destination_id = destination_airport.iata_id AND id = ?",
                new Object[]{id},this);

       if(routes != null && routes.size() > 0)
           return routes.get(0);

       return null;
    }

    public Route getRouteByLocation(String originCity, String destinationCity) throws SQLException, ClassNotFoundException{
        List<Route> routes = jdbcTemplate.query("""
                        SELECT id, origin_id, destination_id, origin_airport.city AS origin_city, destination_airport.city AS destination_city FROM route
                                                JOIN airport origin_airport ON origin_id = origin_airport.iata_id
                                                JOIN airport destination_airport ON destination_id = destination_airport.iata_id\s
                                                AND origin_id = ? AND destination_id = ?
                        """,
                new Object[]{originCity, destinationCity},this);

        if(routes != null && routes.size() > 0)
            return routes.get(0);

        return null;
    }

    @Override
    public List<Route> extractData(ResultSet rs) throws SQLException {
        List<Route> routes = new ArrayList<>();
        while(rs.next()){
            routes.add(Route.toObject(rs));
        }

        return routes;
    }
}
