package com.ss.utopia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController extends BaseController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
