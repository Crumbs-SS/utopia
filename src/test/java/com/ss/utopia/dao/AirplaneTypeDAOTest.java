package com.ss.utopia.dao;

import com.ss.utopia.entity.AirplaneType;
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
class AirplaneTypeDAOTest {

    @Autowired
    AirplaneTypeDAO adao;

    @Test
    @Order(1)
    void addAirplaneType() throws SQLException, ClassNotFoundException {
        AirplaneType airplaneType = new AirplaneType(600);

        int numberOfAirplaneTypes = adao.getAllAirplaneTypes().size();
        adao.addAirplaneType(airplaneType);
        assertEquals(numberOfAirplaneTypes + 1, adao.getAllAirplaneTypes().size());
    }

    @Test
    @Order(2)
    void updateAirplaneType() throws SQLException, ClassNotFoundException {
        AirplaneType airplaneType = adao.getAllAirplaneTypes().get(adao.getAllAirplaneTypes().size() - 1);
        airplaneType.setMaxCapacity(1000);
        adao.updateAirplaneType(airplaneType);

        assertEquals(airplaneType.toString(), adao.getAirplaneTypeByID(airplaneType.getId()).toString());
    }

    @Test
    @Order(3)
    void deleteAirplaneType() throws SQLException, ClassNotFoundException {
        AirplaneType airplaneType = adao.getAllAirplaneTypes().get(adao.getAllAirplaneTypes().size() - 1);

        adao.deleteAirplaneType(airplaneType);
        assertNull(adao.getAirplaneTypeByID(airplaneType.getId()));
    }
}