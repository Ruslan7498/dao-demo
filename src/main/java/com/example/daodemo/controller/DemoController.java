package com.example.daodemo.controller;

import com.example.daodemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    private DemoService service;

    @GetMapping("/products/fetch-product")
    public String getProductName(@RequestParam(required = false) String name) {
        return service.getProductName(name);
    }
}
