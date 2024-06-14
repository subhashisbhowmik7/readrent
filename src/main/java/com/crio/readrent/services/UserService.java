package com.crio.readrent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.crio.readrent.dtos.LoginRequest;
import com.crio.readrent.entities.User;
import com.crio.readrent.entities.UserRole;
import com.crio.readrent.repositories.UserRepository;

public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        return userRepository.save(user);
    }

    public boolean loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return true;
        }
        return false;
    }   
}
