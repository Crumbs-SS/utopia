package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Route;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class FlightDAOTest {
    @Test
    void addFlight() throws SQLException {
        try {
            RouteDAO routeDAO = new RouteDAO();
            AirplaneDAO airplaneDAO = new AirplaneDAO();
            FlightDAO flightDAO = new FlightDAO();

            Route route = routeDAO.getAllRoutes().get(routeDAO.getAllRoutes().size() - 1);
            Airplane airplane = airplaneDAO.getAllAirplanes().get(airplaneDAO.getAllAirplanes().size() - 1);

            Flight flight = new Flight(new Timestamp(99999999), 23, 200.22F);
            flight.setRoute(route);
            flight.setAirplane(airplane);

            flightDAO.addFlight(flight);

            assertEquals(flight.getId(), flightDAO.getFlightFromId(flight.getId()).getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateFlight() throws SQLException {
        try {
            FlightDAO flightDAO = new FlightDAO();
            Flight flight = flightDAO.getAllFlights().get(flightDAO.getAllFlights().size() - 1);
            flight.setSeatPrice(1000F);

            flightDAO.updateFlight(flight);
            assertEquals(flight.toString(), flightDAO.getFlightFromId(flight.getId()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteFlight() throws SQLException {
        try {
            FlightDAO flightDAO = new FlightDAO();
            Flight flight = flightDAO.getAllFlights().get(flightDAO.getAllFlights().size() - 1);

            flightDAO.deleteFlight(flight);
            assertNull(flightDAO.getFlightFromId(flight.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}