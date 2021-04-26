package com.ss.utopia.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/traveler")

public class TravelerService extends BaseService {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
