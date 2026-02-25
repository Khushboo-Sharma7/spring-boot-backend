package com.example.springapp.service;

// import org.springframework.security.core.userdetails.UserDetails;

import com.example.springapp.model.User;

public interface UserService {
    User createUser(User user);
    // User loginUser(User user);
    User getUserByUsername(String name);
    User getUserById(long id);
    // UserDetails loadUserDetails(String username);

}
