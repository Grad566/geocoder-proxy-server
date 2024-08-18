package com.geo.geocoding.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class WelcomeController {
    @GetMapping
    public String greeting() {
        return "Hello! Use swagger documentation.";
    }
}
