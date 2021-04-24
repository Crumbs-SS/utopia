package com.ss.utopia.dao;

import com.ss.utopia.entity.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO extends BaseDAO<Route>{

    public RouteDAO(Connection conn) {
        super(conn);
    }

    public Integer addRoute(Route route) throws SQLException, ClassNotFoundException {
        Integer key = saveWithPK("INSERT INTO route(origin_id, destination_id) VALUES(?, ?)", new Object[]{
                route.getOriAirport().getAirportCode(),
                route.getDesAirport().getAirportCode()
        });

        route.setId(key);
        return key;
    }
    public void updateRoute(Route route) throws SQLException, ClassNotFoundException {
        save("UPDATE route SET origin_id = ?, destination_id = ? WHERE id = ?", new Object[]{
                route.getOriAirport().getAirportCode(),
                route.getDesAirport().getAirportCode(),
                route.getId()
        });
    }
    public void deleteRoute(Route route) throws SQLException, ClassNotFoundException {
        save("DELETE FROM route WHERE id = ?", new Object[] {route.getId()});
    }

    public List<Route> getAllRoutes() throws SQLException, ClassNotFoundException {
        return read("SELECT id, origin_id, destination_id, origin_airport.city AS origin_city, destination_airport.city AS destination_city FROM route\n" +
                "LEFT JOIN airport origin_airport ON origin_id = origin_airport.iata_id\n" +
                "LEFT JOIN airport destination_airport ON destination_id = destination_airport.iata_id", null);
    }

    public Route getRouteById(Integer id) throws SQLException, ClassNotFoundException{
       List<Route> routes = read("SELECT id, origin_id, destination_id, origin_airport.city AS origin_city, destination_airport.city AS destination_city FROM route \n" +
                "JOIN airport origin_airport ON origin_id = origin_airport.iata_id\n" +
                "JOIN airport destination_airport ON destination_id = destination_airport.iata_id AND id = ?",
                new Object[]{id});

       if(routes.size() > 0)
           return routes.get(0);

       return null;
    }

    public Route getRouteByLocation(String originCity, String destinationCity) throws SQLException, ClassNotFoundException{
        List<Route> routes = read("""
                        SELECT id, origin_id, destination_id, origin_airport.city AS origin_city, destination_airport.city AS destination_city FROM route
                                                JOIN airport origin_airport ON origin_id = origin_airport.iata_id
                                                JOIN airport destination_airport ON destination_id = destination_airport.iata_id\s
                                                AND origin_id = ? AND destination_id = ?
                        """,
                new Object[]{originCity, destinationCity});

        if(routes.size() > 0)
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
