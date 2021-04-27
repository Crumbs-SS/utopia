package com.ss.utopia.dao;

import com.ss.utopia.entity.Airport;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AirportDAOTest {

   @Autowired
   AirportDAO adao;

    @Test
    @Order(1)
    void addAirport() throws SQLException, ClassNotFoundException {
        Airport airport = new Airport("NEW", "NEW AIRPORT");
        adao.addAirport(airport);

        assertEquals(airport.toString(), adao.getAirportByAirportCode(airport.getAirportCode()).toString());
    }

    @Test
    @Order(2)
    void updateAirport() throws SQLException, ClassNotFoundException{
        Airport airport = adao.getAirportByAirportCode("NEW");
        airport.setCityName("UDPATE!");

        adao.updateAirport(airport);
        assertEquals(airport.toString(), adao.getAirportByAirportCode(airport.getAirportCode()).toString());
    }

    @Test
    @Order(3)
    void deleteAirport() throws SQLException, ClassNotFoundException{
        Airport airport = adao.getAirportByAirportCode("NEW");
        adao.deleteAirport(airport);

        assertNull(adao.getAirportByAirportCode("NEW"));
    }
}