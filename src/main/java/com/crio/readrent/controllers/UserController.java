package com.crio.readrent.controllers;

import com.crio.readrent.dtos.AuthRequest;
import com.crio.readrent.dtos.AuthResponse;
import com.crio.readrent.dtos.RegisterRequest;
import com.crio.readrent.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(201).body(authService.register(request));
    }

    // @PostMapping("/login")
    // public ResponseEntity<String> loginUser(@RequestBody User user) {
    //     User foundUser = userService.getUserByEmail(user.getEmail());

    //     if (foundUser != null && new BCryptPasswordEncoder().matches(user.getPassword(), foundUser.getPassword())) {
    //         return ResponseEntity.ok("DummyJWTToken");
    //     } else {
    //         return ResponseEntity.status(401).body("Invalid credentials");
    //     }
    // }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
