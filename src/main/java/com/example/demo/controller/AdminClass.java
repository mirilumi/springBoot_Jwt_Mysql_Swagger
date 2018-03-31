package com.example.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Check Admin",description = "Check if is accessed only by Admin")
@RestController
@RequestMapping("/api/admin")
public class AdminClass {
    public String adminRights(){
        return "This is for admin users";
    }
}
