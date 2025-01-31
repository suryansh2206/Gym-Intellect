package com.knowit.gymintellect.gym_intellect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.gymintellect.gym_intellect.dto.JwtResponse;
import com.knowit.gymintellect.gym_intellect.dto.LoginRequest;
import com.knowit.gymintellect.gym_intellect.dto.SignupRequest;
import com.knowit.gymintellect.gym_intellect.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequest signupRequest) {
        userService.registerUser(signupRequest);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = userService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

}
