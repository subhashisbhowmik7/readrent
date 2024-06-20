package com.crio.readrent.controllers;

import com.crio.readrent.entities.User;
import com.crio.readrent.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.status(201).body(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        User foundUser = userService.getUserByEmail(user.getEmail());

        if (foundUser != null && new BCryptPasswordEncoder().matches(user.getPassword(), foundUser.getPassword())) {
            return ResponseEntity.ok("DummyJWTToken");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
