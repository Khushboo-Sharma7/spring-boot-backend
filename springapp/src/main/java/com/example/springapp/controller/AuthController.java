package com.example.springapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapp.configuration.JwtUtils;
import com.example.springapp.model.User;
import com.example.springapp.service.UserServiceImpl;



@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/signUp")
    public ResponseEntity<?> register(@RequestBody User user) {
        User u = userService.createUser(user);
        if(u!=null)
        {
            return ResponseEntity.status(201).body(u);
        }
        return ResponseEntity.status(400).build();
    }
    
    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User user) {

    try {
        // STEP 1: Let Spring Security authenticate
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword()
            )
        );

        // STEP 2: Authentication successful
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // STEP 3: Fetch user info (NOT password)
        User dbUser = userService.getUserByUsername(userDetails.getUsername());

        // STEP 4: Generate JWT
        String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(Map.of(
            "token", jwt,
            "user", Map.of(
                "id", dbUser.getId(),
                "username", dbUser.getUsername(),
                "role", dbUser.getRole()
            )
        ));

    } catch (BadCredentialsException e) {
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable long userId) {
        User u = userService.getUserById(userId);
        if(u!=null)
        {
            return ResponseEntity.status(200).body(u);
        }
        return ResponseEntity.status(400).build();
    }
    
}
