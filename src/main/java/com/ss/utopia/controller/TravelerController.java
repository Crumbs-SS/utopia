package com.ss.utopia.controller;

import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.AirplaneType;
import com.ss.utopia.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/traveler")
public class TravelerController {

    @Autowired
    TravelerService travelerService;

    @GetMapping("/planetypes")
    public List<AirplaneType> getAirplaneTypes(){
        return travelerService.getAllAirplaneTypes();
    }

    @GetMapping("/planes/{maxCapacity}")
    public List<Airplane> getAirplaneTypes(@PathVariable String maxCapacity ){
        return travelerService.getAirplanesByCapacity(maxCapacity);
    }

    @GetMapping("/airplanes")
    public List<Airplane> getAirplanes(){
        return travelerService.getAirplanes();
    }

}
