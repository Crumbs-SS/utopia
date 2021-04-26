package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.AirplaneTypeDAO;
import com.ss.utopia.entity.AirplaneType;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AirplaneTypeDAOTest {
    @Test
    void addAirplaneType() throws SQLException {
        try{
            AirplaneTypeDAO adao = new AirplaneTypeDAO();
            AirplaneType airplaneType = new AirplaneType(600);

            adao.addAirplaneType(airplaneType);
            assertEquals(airplaneType.toString(), adao.getAirplaneTypeByID(airplaneType.getId()).toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void updateAirplaneType() throws SQLException {
        try{
            AirplaneTypeDAO adao = new AirplaneTypeDAO();
            AirplaneType airplaneType = adao.getAllAirplaneTypes().get(adao.getAllAirplaneTypes().size() - 1);
            airplaneType.setMaxCapacity(1000);
            adao.updateAirplaneType(airplaneType);

            assertEquals(airplaneType.toString(), adao.getAirplaneTypeByID(airplaneType.getId()).toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void deleteAirplaneType() throws SQLException {
        try{
            AirplaneTypeDAO adao = new AirplaneTypeDAO();
            AirplaneType airplaneType = adao.getAllAirplaneTypes().get(adao.getAllAirplaneTypes().size() - 1);

            adao.deleteAirplaneType(airplaneType);
            assertNull(adao.getAirplaneTypeByID(airplaneType.getId()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}