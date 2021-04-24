package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.entity.Airport;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AirportDAOTest {
    ConnectionUtil connUtil = new ConnectionUtil();

    @Test
    void addAirport() throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            AirportDAO adao = new AirportDAO(conn);

            Airport airport = new Airport("NEW", "NEW AIRPORT");
            adao.addAirport(airport);

            assertEquals(airport.toString(), adao.getAirportByAirportCode(airport.getAirportCode()).toString());
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally{
            conn.close();
        }
    }

    @Test
    void updateAirport() throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            AirportDAO adao = new AirportDAO(conn);

            Airport airport = adao.getAirportByAirportCode("NEW");
            airport.setCityName("UDPATE!");

            adao.updateAirport(airport);

            assertEquals(airport.toString(), adao.getAirportByAirportCode(airport.getAirportCode()).toString());
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally{
            conn.close();
        }
    }

    @Test
    void deleteAirport() throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            AirportDAO adao = new AirportDAO(conn);

            Airport airport = adao.getAirportByAirportCode("NEW");
            adao.deleteAirport(airport);

            assertNull(adao.getAirportByAirportCode("NEW"));
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally{
            conn.close();
        }
    }
}