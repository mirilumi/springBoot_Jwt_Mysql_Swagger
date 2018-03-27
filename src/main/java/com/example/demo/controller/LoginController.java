package com.example.demo.controller;


import com.example.demo.dtos.LoginDtos;
import com.example.demo.entity.User;
import com.example.demo.security.JwtToken;
import com.example.demo.security.JwtTokenGenerater;
import com.example.demo.security.UserContext;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth/loginn")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenGenerater jwtTokenGenerater;
//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public JwtToken hello(){
//        User user = userService.getByUsername("svlada@gmail.com");
////                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + "svlada@gmail.com"));
//
//        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
//        List<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
//                .collect(Collectors.toList());
//
//        UserContext userContext = UserContext.create(user.getUsername(), authorities);
//
//        return jwtTokenGenerater.createToken(userContext);
//    }
    @PostMapping("/api/test")
    public JwtToken test(@RequestBody LoginDtos loginDtos){
        LoginDtos test = loginDtos;
        User user = userService.getByUsername("svlada@gmail.com");
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + "svlada@gmail.com"));

        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getUsername(), authorities);

        return jwtTokenGenerater.createToken(userContext);
    }
}
