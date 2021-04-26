package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.entity.Airport;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AirportDAOTest {
    @Test
    void addAirport() throws SQLException {
        try {
            AirportDAO adao = new AirportDAO();

            Airport airport = new Airport("NEW", "NEW AIRPORT");
            adao.addAirport(airport);

            assertEquals(airport.toString(), adao.getAirportByAirportCode(airport.getAirportCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateAirport() throws SQLException {
        try {
            AirportDAO adao = new AirportDAO();

            Airport airport = adao.getAirportByAirportCode("NEW");
            airport.setCityName("UDPATE!");

            adao.updateAirport(airport);

            assertEquals(airport.toString(), adao.getAirportByAirportCode(airport.getAirportCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteAirport() throws SQLException {
        try {
            AirportDAO adao = new AirportDAO();

            Airport airport = adao.getAirportByAirportCode("NEW");
            adao.deleteAirport(airport);

            assertNull(adao.getAirportByAirportCode("NEW"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}