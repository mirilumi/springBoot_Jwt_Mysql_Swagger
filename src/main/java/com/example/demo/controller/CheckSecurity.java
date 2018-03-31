package com.example.demo.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Check Security",description = "Check if security works")
@RestController
@RequestMapping("/api/check")
public class CheckSecurity {

    @GetMapping
    public String authenticated(){
        return "This is for authenticated users";
    }
}
