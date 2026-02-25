package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springapp.model.Cart;

public interface CartRepo extends JpaRepository<Cart, Long>{
    Cart findByUserId(long id);
}
