//package com.example.demo.controller;
//
//
//import com.example.demo.dtos.LoginDtos;
//import com.example.demo.entity.User;
//import com.example.demo.entity.UserRole;
//import com.example.demo.security.JwtToken;
//import com.example.demo.security.JwtTokenGenerater;
//import com.example.demo.security.Token;
//import com.example.demo.security.UserContext;
//import com.example.demo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping
//public class LoginController {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private JwtTokenGenerater jwtTokenGenerater;
//    @PostMapping("/api/login")
//    public Token login(@RequestBody LoginDtos loginDtos){
//        User user = userService.getByUsername(loginDtos.getUsername());
////                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + "svlada@gmail.com"));
//        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
//        List<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
//                .collect(Collectors.toList());
//
//        UserContext userContext = UserContext.create(user.getUsername(), authorities);
//
//        return jwtTokenGenerater.createToken(userContext);
//    }
//}
