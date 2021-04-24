package com.ss.utopia.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminService extends BaseService {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
