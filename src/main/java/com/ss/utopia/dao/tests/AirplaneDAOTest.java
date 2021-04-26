package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.AirplaneTypeDAO;
import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.AirplaneType;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AirplaneDAOTest {
    @Test
    void addAirplane() throws SQLException {
        try{
            AirplaneDAO planeDAO = new AirplaneDAO();
            AirplaneTypeDAO typeDAO = new AirplaneTypeDAO();

            AirplaneType airplaneType = typeDAO.getAllAirplaneTypes().get(typeDAO.getAllAirplaneTypes().size() - 1);
            Airplane airplane = new Airplane(airplaneType);

            planeDAO.addAirplane(airplane);
            assertEquals(airplane.toString(), planeDAO.getAirplaneById(airplane.getId()).toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void updateAirplane() throws SQLException {
        Connection conn = null;
        try{
            AirplaneDAO planeDAO = new AirplaneDAO();
            AirplaneTypeDAO typeDAO = new AirplaneTypeDAO();

            AirplaneType airplaneType = typeDAO.getAllAirplaneTypes().get(typeDAO.getAllAirplaneTypes().size() - 2);
            Airplane airplane = planeDAO.getAllAirplanes().get(planeDAO.getAllAirplanes().size() - 1);
            airplane.setAirplaneType(airplaneType);

            planeDAO.updateAirplane(airplane);
            assertEquals(airplane.toString(), planeDAO.getAirplaneById(airplane.getId()).toString());

            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void deleteAirplane() throws SQLException {
        try{
            AirplaneDAO planeDAO = new AirplaneDAO();

            Airplane airplane = planeDAO.getAllAirplanes().get(planeDAO.getAllAirplanes().size() - 1);
            planeDAO.deleteAirplane(airplane);

            assertNull(planeDAO.getAirplaneById(airplane.getId()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}