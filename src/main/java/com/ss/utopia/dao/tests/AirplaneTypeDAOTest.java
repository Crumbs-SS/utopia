package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.AirplaneTypeDAO;
import com.ss.utopia.entity.AirplaneType;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AirplaneTypeDAOTest {
    ConnectionUtil connUtil = new ConnectionUtil();

    @Test
    void addAirplaneType() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            AirplaneTypeDAO adao = new AirplaneTypeDAO(conn);
            AirplaneType airplaneType = new AirplaneType(600);

            adao.addAirplaneType(airplaneType);
            assertEquals(airplaneType.toString(), adao.getAirplaneTypeByID(airplaneType.getId()).toString());

            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally{
            conn.close();
        }
    }

    @Test
    void updateAirplaneType() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            AirplaneTypeDAO adao = new AirplaneTypeDAO(conn);
            AirplaneType airplaneType = adao.getAllAirplaneTypes().get(adao.getAllAirplaneTypes().size() - 1);
            airplaneType.setMaxCapacity(1000);
            adao.updateAirplaneType(airplaneType);

            assertEquals(airplaneType.toString(), adao.getAirplaneTypeByID(airplaneType.getId()).toString());
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally{
            conn.close();
        }
    }

    @Test
    void deleteAirplaneType() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            AirplaneTypeDAO adao = new AirplaneTypeDAO(conn);
            AirplaneType airplaneType = adao.getAllAirplaneTypes().get(adao.getAllAirplaneTypes().size() - 1);

            adao.deleteAirplaneType(airplaneType);
            assertNull(adao.getAirplaneTypeByID(airplaneType.getId()));
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally{
            conn.close();
        }
    }
}