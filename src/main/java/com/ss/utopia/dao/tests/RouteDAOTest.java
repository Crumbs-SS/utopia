package com.ss.utopia.dao.tests;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Route;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RouteDAOTest {

    @org.junit.jupiter.api.Test
    void addRoute() throws SQLException {
        try {
            Airport origin = new Airport("ATL", "ATLANTA");
            Airport destination = new Airport("MIA", "MIAMI");
            Route route = new Route(origin, destination);

            RouteDAO rdao = new RouteDAO();
            Integer key = rdao.addRoute(route);

            assertEquals(route.toString(), rdao.getRouteById(key).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void updateRoute() throws SQLException {
        try {
            RouteDAO rdao = new RouteDAO();
            List<Route> routes = rdao.getAllRoutes();
            Route route = routes.get(routes.size() - 1);
            Airport origin = new Airport("LAX", "LOS ANGELES");
            Airport destination = new Airport("PHL", "PHILADELPHIA");

            route.setOriAirport(origin);
            route.setDesAirport(destination);

            rdao.updateRoute(route);

            assertEquals(route.toString(), rdao.getRouteById(route.getId()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void deleteRoute() throws SQLException{
        try {
            RouteDAO rdao = new RouteDAO();

            List<Route> routes = rdao.getAllRoutes();
            Route route = routes.get(routes.size() - 1);
            rdao.deleteRoute(route);

            assertNull( rdao.getRouteById(route.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}