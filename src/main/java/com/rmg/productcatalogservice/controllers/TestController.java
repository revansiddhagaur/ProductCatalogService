package com.rmg.productcatalogservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String welcome(){
        return "Welcome to Product Catalog Service";
    }

}
