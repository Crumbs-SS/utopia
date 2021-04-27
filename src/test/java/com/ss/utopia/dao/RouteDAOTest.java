package com.ss.utopia.dao;

import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Route;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RouteDAOTest {

    @Autowired
    RouteDAO rdao;

    @Test
    @Order(1)
    void addRoute() throws SQLException, ClassNotFoundException {
        Airport origin = new Airport("ATL", "ATLANTA");
        Airport destination = new Airport("MIA", "MIAMI");
        Route route = new Route(origin, destination);

        int amountOfRoutes = rdao.getAllRoutes().size();
        rdao.addRoute(route);

        assertEquals(amountOfRoutes + 1, rdao.getAllRoutes().size());
    }

    @Test
    @Order(2)
    void updateRoute() throws SQLException, ClassNotFoundException {
        List<Route> routes = rdao.getAllRoutes();
        Route route = routes.get(routes.size() - 1);
        Airport origin = new Airport("LAX", "LOS ANGELES");
        Airport destination = new Airport("PHL", "PHILADELPHIA");

        route.setOriAirport(origin);
        route.setDesAirport(destination);

        rdao.updateRoute(route);

        assertEquals(route.toString(), rdao.getRouteById(route.getId()).toString());
    }

    @Test
    @Order(3)
    void deleteRoute() throws SQLException, ClassNotFoundException{
        Route route = rdao.getRouteByLocation("LAX", "PHL");
        rdao.deleteRoute(route);

        assertNull( rdao.getRouteById(route.getId()));
    }

}