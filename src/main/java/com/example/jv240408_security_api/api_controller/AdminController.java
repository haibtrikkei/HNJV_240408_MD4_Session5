package com.example.jv240408_security_api.api_controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @GetMapping
    public ResponseEntity<?> demo(){
        return new ResponseEntity<>(List.of("admin1","admin2","amdin3"), HttpStatus.OK);
    }
}
