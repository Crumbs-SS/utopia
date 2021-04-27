package com.ss.utopia.dao;

import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Route;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FlightDAOTest {

    @Autowired
    RouteDAO routeDAO;

    @Autowired
    AirplaneDAO airplaneDAO;

    @Autowired
    FlightDAO flightDAO;

    @Test
    @Order(1)
    void addFlight() throws SQLException, ClassNotFoundException {
        Route route = routeDAO.getAllRoutes().get(routeDAO.getAllRoutes().size() - 1);
        Airplane airplane = airplaneDAO.getAllAirplanes().get(airplaneDAO.getAllAirplanes().size() - 1);

        Flight flight = new Flight(new Timestamp(99999999), 23, 200.22F);
        flight.setRoute(route);
        flight.setAirplane(airplane);

        flightDAO.addFlight(flight);

        assertEquals(flight.getId(), flightDAO.getFlightFromId(flight.getId()).getId());
    }

    @Test
    @Order(2)
    void updateFlight() throws SQLException, ClassNotFoundException {
        Flight flight = flightDAO.getAllFlights().get(flightDAO.getAllFlights().size() - 1);
        flight.setSeatPrice(1000F);

        flightDAO.updateFlight(flight);
        assertEquals(flight.toString(), flightDAO.getFlightFromId(flight.getId()).toString());
    }

    @Test
    @Order(3)
    void deleteFlight() throws SQLException, ClassNotFoundException {
        Flight flight = flightDAO.getAllFlights().get(flightDAO.getAllFlights().size() - 1);

        flightDAO.deleteFlight(flight);
        assertNull(flightDAO.getFlightFromId(flight.getId()));
    }
}