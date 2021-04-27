package com.ss.utopia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/traveler")
public class TravelerController extends BaseController {

    @GetMapping("/flights")
    public String getAllFlights() {
        return "Hello World!";
    }
}
