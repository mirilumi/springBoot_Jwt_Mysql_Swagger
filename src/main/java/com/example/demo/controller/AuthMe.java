package com.example.demo.controller;

import com.example.demo.security.JwtAuthenticationToken;
import com.example.demo.security.UserContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/me")
public class AuthMe {

    @GetMapping
    public UserContext authMe(JwtAuthenticationToken token){
        return (UserContext) token.getPrincipal();
    }
}
