// package com.crio.readrent.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.crio.readrent.dtos.LoginRequest;
// import com.crio.readrent.entities.User;
// import com.crio.readrent.services.*;

//  @RestController
// @RequestMapping("/users")
// public class UserController {

//     @Autowired
//     private UserService userService;

//     @PostMapping("/register")
//     public ResponseEntity<User> registerUser(@RequestBody User user) {
//         User registeredUser = userService.registerUser(user);
//         return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
//     }

//     @PostMapping("/login")
//     public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
//         boolean isAuthenticated = userService.loginUser(loginRequest);
//         if (isAuthenticated) {
//             return new ResponseEntity<>("Login successful", HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
//         }
//     }
// }
   

