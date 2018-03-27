package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
//
////    @Autowired
////    public UserService(UserRepository userRepository) {
////        this.userRepository = userRepository;
////    }
//
////    public UserRepository getUserRepository() {
////        return userRepository;
////    }
//
//
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
//
//
//    public List<User> findAll() {
//        return userRepository.findAll();
//    }


}
