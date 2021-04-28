package com.ss.utopia.service;

import com.ss.utopia.dao.AirplaneRepository;
import com.ss.utopia.dao.AirplaneTypeRepository;
import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.AirplaneType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = { Exception.class })
public class TravelerService {
    @Autowired
    AirplaneRepository airplaneRepository;

    @Autowired
    AirplaneTypeRepository airplaneTypeRepository;

    public List<AirplaneType> getAllAirplaneTypes(){
        return airplaneTypeRepository.findAll();
    }

    public List<Airplane> getAirplanesByCapacity(String maxCapacity) {
        return airplaneRepository.findAirplanesByMaxCapacity(Integer.parseInt(maxCapacity));
    }

    public List<Airplane> getAirplanes(){
        return airplaneRepository.findAll();
    }
}
