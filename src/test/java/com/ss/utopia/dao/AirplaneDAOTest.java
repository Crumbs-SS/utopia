package com.ss.utopia.dao;

import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.AirplaneType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AirplaneDAOTest {

    @Autowired
    AirplaneDAO planeDAO;

    @Autowired
    AirplaneTypeDAO typeDAO;

    @Test
    @Order(1)
    void addAirplane() throws SQLException, ClassNotFoundException{
        AirplaneType airplaneType = typeDAO.getAllAirplaneTypes().get(typeDAO.getAllAirplaneTypes().size() - 1);
        Airplane airplane = new Airplane(airplaneType);
        int numberOfAirplanes = planeDAO.getAllAirplanes().size();

        planeDAO.addAirplane(airplane);
        assertEquals(numberOfAirplanes + 1, planeDAO.getAllAirplanes().size());
    }

    @Test
    @Order(2)
    void updateAirplane() throws SQLException, ClassNotFoundException{
        AirplaneType airplaneType = typeDAO.getAllAirplaneTypes().get(typeDAO.getAllAirplaneTypes().size() - 2);
        Airplane airplane = planeDAO.getAllAirplanes().get(planeDAO.getAllAirplanes().size() - 1);
        airplane.setAirplaneType(airplaneType);

        planeDAO.updateAirplane(airplane);
        assertEquals(airplane.toString(), planeDAO.getAirplaneById(airplane.getId()).toString());
    }

    @Test
    @Order(3)
    void deleteAirplane() throws SQLException, ClassNotFoundException{
        Airplane airplane = planeDAO.getAllAirplanes().get(planeDAO.getAllAirplanes().size() - 1);
        planeDAO.deleteAirplane(airplane);

        assertNull(planeDAO.getAirplaneById(airplane.getId()));
    }
}