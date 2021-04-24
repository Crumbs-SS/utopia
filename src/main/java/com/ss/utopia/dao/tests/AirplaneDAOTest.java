package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.AirplaneTypeDAO;
import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.AirplaneType;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AirplaneDAOTest {
    ConnectionUtil connUtil = new ConnectionUtil();

    @Test
    void addAirplane() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            AirplaneDAO planeDAO = new AirplaneDAO(conn);
            AirplaneTypeDAO typeDAO = new AirplaneTypeDAO(conn);

            AirplaneType airplaneType = typeDAO.getAllAirplaneTypes().get(typeDAO.getAllAirplaneTypes().size() - 1);
            Airplane airplane = new Airplane(airplaneType);

            planeDAO.addAirplane(airplane);
            assertEquals(airplane.toString(), planeDAO.getAirplaneById(airplane.getId()).toString());

            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally{
            conn.close();
        }
    }

    @Test
    void updateAirplane() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            AirplaneDAO planeDAO = new AirplaneDAO(conn);
            AirplaneTypeDAO typeDAO = new AirplaneTypeDAO(conn);

            AirplaneType airplaneType = typeDAO.getAllAirplaneTypes().get(typeDAO.getAllAirplaneTypes().size() - 2);
            Airplane airplane = planeDAO.getAllAirplanes().get(planeDAO.getAllAirplanes().size() - 1);
            airplane.setAirplaneType(airplaneType);

            planeDAO.updateAirplane(airplane);
            assertEquals(airplane.toString(), planeDAO.getAirplaneById(airplane.getId()).toString());

            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally{
            conn.close();
        }
    }

    @Test
    void deleteAirplane() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            AirplaneDAO planeDAO = new AirplaneDAO(conn);

            Airplane airplane = planeDAO.getAllAirplanes().get(planeDAO.getAllAirplanes().size() - 1);
            planeDAO.deleteAirplane(airplane);

            assertNull(planeDAO.getAirplaneById(airplane.getId()));
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally{
            conn.close();
        }
    }
}