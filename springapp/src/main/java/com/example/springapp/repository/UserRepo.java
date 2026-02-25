package com.example.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springapp.model.User;


public interface UserRepo extends JpaRepository<User,Long>{
    Optional<User> getUserByUsername(String username);
}
