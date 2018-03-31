package com.example.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Check User",description = "Check if is accessed  by any user")
@RestController
@RequestMapping("/api/user")
public class UserClass {
    public String userRights(){
        return "This is for any users";
    }
}
