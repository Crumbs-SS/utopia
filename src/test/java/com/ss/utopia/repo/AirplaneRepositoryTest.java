package com.ss.utopia.repo;

import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.AirplaneType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AirplaneRepositoryTest {

    @Autowired AirplaneRepository airplaneRepository;
    @Autowired AirplaneTypeRepository airplaneTypeRepository;


    @Test
    void findAirplanesByMaxCapacity() {
        Integer maxCapacity = 999999;
        AirplaneType airplaneType = airplaneTypeRepository.save(new AirplaneType(maxCapacity));
        airplaneRepository.save(new Airplane(airplaneType));

        Integer amountOfAirplanes = airplaneRepository.findAirplanesByMaxCapacity(maxCapacity).size();
        airplaneTypeRepository.delete(airplaneType);

        assertEquals(1, amountOfAirplanes);
    }
}