package com.example.daodemo.service;

import com.example.daodemo.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    @Autowired
    private DemoRepository repository;

    public String getProductName(String name) {
        return repository.getProductName(name);
    }
}
